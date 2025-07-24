package tree;

import java.io.*;
import java.util.*;

public class TREE2533 {
	static List<List<Integer>> edges = new ArrayList<>();
	static List<List<Integer>> childs = new ArrayList<>();
	static int[] parents;
	static int[][] dp;
	static int INF = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		// dp[n][0]: n번 노드가 얼리 아답터일 때의 최소 얼리 어답터의 수
		// dp[n][1]: n번 노드가 얼리 어답터가 아닐때의 최소 얼리 어답터의 수
		dp = new int[N+1][2]; 
		
		// 초기화
		for(int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
			childs.add(new ArrayList<>());
			parents[i] = i;
		}
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 2; j++) {
				dp[i][j] = INF;
			}
		}
		
		// 트리의 에지를 입력받아 저장
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			edges.get(u).add(v);
			edges.get(v).add(u);
		}
		
		// 루트노드가 1인 트리 구성
		makeTree(1, -1);
		
		// dfs를 사용해 dp값을 갱신하고 최종적으로 루트에 누적된 2가지 값 중 작은 값을 선택
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
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
		dp[curr][0] = 1; // 현재 노드가 얼리 어답터인 경우
		dp[curr][1] = 0; // 현재 노드가 얼리 어답터가 아닌 경우
		
		for(int child : childs.get(curr)) {
			dfs(child);
			dp[curr][0] += Math.min(dp[child][0], dp[child][1]);
			dp[curr][1] += dp[child][0]; // 현재 노드가 얼리 어답터가 아니라면 자식 노드는 얼리 어답터여야 함
		}
	}
}