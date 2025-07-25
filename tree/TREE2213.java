package tree;

import java.io.*;
import java.util.*;

public class TREE2213 {
	static int[] weights;
	static List<List<Integer>> edges = new ArrayList<>();
	static List<List<Integer>> childs = new ArrayList<>();
	static int[][] dp;
	static List<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		weights = new int[N+1];
		// n번 정점을 루트로 하는 서브트리에서
		// n번 정점이 독립집합에 포함된 경우와 독립집합에 포함되지 않은 경우의
		// 최대 독립 집합의 크기를 저장
		dp = new int[N+1][2];
		
		for(int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
			childs.add(new ArrayList<>());
		}
		
		// 각 정점의 가중치를 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		// 트리의 에지를 입력받아 저장
		while(true) {
			String line = br.readLine();
			if(line == null || line.equals("")) break;
			
			st = new StringTokenizer(line);
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
		
		makeTree(1, -1);
		dfs(1);
		trace(1, true);
		Collections.sort(result);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < result.size(); i++) {
			sb.append(result.get(i)).append(" ");
		}

	
		System.out.println(Math.max(dp[1][0], dp[1][1]));
		System.out.println(sb);
	}
	
	private static void makeTree(int curr, int parent) {
		for(int child : edges.get(curr)) {
			if(child != parent) {
				childs.get(curr).add(child);
				makeTree(child, curr);
			}
		}
	}
	
	private static void dfs(int curr) {
		dp[curr][0] = weights[curr]; // 현재 정점이 독립집합에 포함된 경우
		dp[curr][1] = 0; // 현재 정점이 독립집합에 포함되지 않은 경우
		
		for(int child : childs.get(curr)) {
			dfs(child);
			dp[curr][0] += dp[child][1]; // 현재 정점이 독립집합에 포함된 경우 자식 정점은 포함될 수 없음
			dp[curr][1] += Math.max(dp[child][0], dp[child][1]); // 현재 정점이 독립집합에 포함되지 않은 경우 자식 정점은 포함될 수도 안될 수도 있음
		}
	}
	
	private static void trace(int curr, boolean isPossible) {
		if(isPossible && dp[curr][0] > dp[curr][1]) {
			result.add(curr);
			for(int child : childs.get(curr)) {
				trace(child, false);
			}
		}
		
		else {
			for(int child : childs.get(curr)) {
				trace(child, true);
			}
		}
	}
}