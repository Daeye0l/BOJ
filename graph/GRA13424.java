package graph;

import java.io.*;
import java.util.*;

public class GRA13424 {
	static List<List<Node>> graph;
	static int INF = 5_000_000;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for(int n = 0; n <= N; n++) {
				graph.add(new ArrayList<>());
			}
			
			for(int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(new Node(b, c));
				graph.get(b).add(new Node(a, c));
			}
			
			int K = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int[] cumulDistance = new int[N+1];
			for(int k = 0; k < K; k++) {
				int f = Integer.parseInt(st.nextToken());
				int[] distance = dijkstra(N, f);
				
				for(int n = 1; n <= N; n++) {
					cumulDistance[n] += distance[n];
				}
			}
			
			int min = INF;
			int loc = 0;;
			for(int n = 1; n <= N; n++) {
				if(cumulDistance[n] < min) {
					min = cumulDistance[n];
					loc = n;
				}
			}
			result.append(loc).append('\n');
		}
		
		System.out.println(result);
	}
	
	private static int[] dijkstra(int N, int start) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		for(int i = 1; i <= N; i++) {
			distance[i] = INF;
		}
		
		distance[start] = 0;
		pqueue.offer(new Node(start, 0));
		
		while(!pqueue.isEmpty()) {
			Node curr = pqueue.poll();
			
			if(visited[curr.num]) continue;
			visited[curr.num] = true;
			
			for(Node next : graph.get(curr.num)) {
				if(distance[curr.num] + next.cost < distance[next.num]) {
					distance[next.num] = distance[curr.num] + next.cost;
					pqueue.offer(new Node(next.num, distance[next.num]));
				}
			}
		}
		
		return distance;
	}
	
	private static class Node implements Comparable<Node>{
		private int num;
		private int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}