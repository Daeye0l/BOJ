package graph;

import java.io.*;
import java.util.*;

public class GRA2056 {
	static List<List<Integer>> graph;
	static int[] indegree;
	static int[] cost;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		cost = new int[N+1];
		dp = new int[N+1];
		
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int c = Integer.parseInt(st.nextToken());
			cost[i] = c;
			dp[i] = c;
			
			int cnt = Integer.parseInt(st.nextToken());	
			for(int j = 0; j < cnt; j++) {
				int prev = Integer.parseInt(st.nextToken());
				
				indegree[i]++;
				graph.get(prev).add(i);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		topologicalSort(queue);
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
	
	private static void topologicalSort(Queue<Integer> queue) {
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : graph.get(curr)) {
				dp[next] = Math.max(dp[next], dp[curr] + cost[next]);
				
				indegree[next]--;
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}