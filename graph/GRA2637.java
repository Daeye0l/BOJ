package graph;

import java.io.*;
import java.util.*;

public class GRA2637 {
	static List<List<Integer>> graph;
	static int[][] dp, cnt;
	static int[] indegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			graph.get(Y).add(X);
			indegree[X]++;
			cnt[X][Y] = K;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		List<Integer> basic = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			// 진입차수가 없는 것이 기본 부품
			if(indegree[i] == 0) {
				queue.offer(i);
				basic.add(i);
				dp[i][i] = 1;
				cnt[i][i] = 1;
			}
		}
		
		topologicalSort(N, queue, basic);
		
		StringBuilder result = new StringBuilder();
		for(int b : basic) {
			result.append(b).append(' ').append(dp[N][b]).append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		dp = new int[N+1][N+1]; // i번 부품을 만드는 데 필요한 j번 기본 부품의 개수
		cnt = new int[N+1][N+1]; // i번 부품을 만드는 데 필요한 j번 부품의 개수
		indegree = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
	}
	
	private static void topologicalSort(int N, Queue<Integer> queue, List<Integer> basic) {
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : graph.get(curr)) {
				for(int b : basic) {
					dp[next][b] += dp[curr][b] * cnt[next][curr];
				}
				indegree[next]--;
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}