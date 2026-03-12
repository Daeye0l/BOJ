package graph;

import java.io.*;
import java.util.*;

public class GRA14284 {
	static List<List<Node>> graph = new ArrayList<>();
	static int[] distance;
	static boolean[] visited;
	static int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		init(n);
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		System.out.println(dijkstra(s, t));
	}
	
	private static int dijkstra(int s, int t) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		
		distance[s] = 0;
		pqueue.offer(new Node(s, 0));
		
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
		
		return distance[t];
	}
	
	private static void init(int n) {
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		distance = new int[n+1];
		for(int i = 1; i <= n; i++) {
			distance[i] = INF;
		}
		visited = new boolean[n+1];
	}
	
	private static class Node implements Comparable<Node> {
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