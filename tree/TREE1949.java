package tree;

import java.io.*;
import java.util.*;

public class TREE1949 {
	static List<List<Integer>> edges = new ArrayList<>();
	static List<List<Integer>> childs = new ArrayList<>();
	static int[] population;
	static int[] parents;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		population = new int[N+1];
		parents = new int[N+1];
		// n번 마을을 루트로 하는 서브트리에서 n번 마을이 우수마을인 경우와 우수마을이 아닌 경우의 주민 수의 최대값을 저장
		dp = new int[N+1][2];
		
		// 초기화
		for(int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
			childs.add(new ArrayList<>());
			parents[i] = i;
		}
		
		// 각 마을의 주민 수를 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접한 두 마을의 에지를 저장
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			edges.get(A).add(B);
			edges.get(B).add(A);
		}
		
		// 루트가 1인 트리 구성
		makeTree(1, -1);
		
		dfs(1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	private static void makeTree(int curr, int parent) {
		for(int child : edges.get(curr)) {
			if(child != parent) {
				childs.get(curr).add(child);
				parents[child] = curr;
				makeTree(child, curr);
			}
		}
	}
	
	private static void dfs(int curr) {
		dp[curr][0] = population[curr]; // 현재 마을이 우수마을인 경우
		dp[curr][1] = 0; // 현재 마을이 우수마을이 아닌 경우
		
		for(int child : childs.get(curr)) {
			dfs(child);
			// 현재 마을이 우수마을이라면 인접한 마을은 우수마을일 수 없음
			dp[curr][0] += dp[child][1];
			// 현재 마을이 우수마을이 아니라면 인접한 마을은 두 개 모두 가능
			dp[curr][1] += Math.max(dp[child][0], dp[child][1]);
		}
	}
}