package graph;

import java.io.*;
import java.util.*;

public class GRA14567 {
	static List<List<Integer>> graph;
	static int[] indegree, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		dp = new int[N+1]; // i번 과목을 이수하는 최소 학기
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			graph.get(A).add(B);
			indegree[B]++;
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				dp[i] = 1; // 선수 과목이 없는 경우 1학기 만에 이수 가능
				queue.offer(i);
			}
		}
		
		topologicalSort(queue);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(dp[i]).append(' ');
		}
		
		System.out.println(sb);
	}
	
	private static void topologicalSort(Queue<Integer> queue) {
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : graph.get(curr)) {
				indegree[next]--;
				// 한 과목에 대해 진입 경로가 여러개인 경우 더 긴쪽이 끝나야 모두 끝남
				dp[next] = Math.max(dp[next], dp[curr] + 1);
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}