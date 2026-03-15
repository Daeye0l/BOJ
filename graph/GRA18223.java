package graph;

import java.io.*;
import java.util.*;

public class GRA18223 {
	static List<List<Node>> graph = new ArrayList<>();
	static boolean[] visited;
	static int[] distance;
	static int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		int[] distance1 = dijkstra(V, 1);
		int[] distance2 = dijkstra(V, P);
		
		if(distance1[V] == distance2[1] + distance2[V]) {
			System.out.println("SAVE HIM");
		}
		else System.out.println("GOOD BYE");
	}
	
	private static int[] dijkstra(int V, int start) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		distance = new int[V+1];
		for(int i = 1; i <= V; i++) {
			distance[i] = INF;
		}
		visited = new boolean[V+1];
		
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
	
	private static void init(int V) {
		
		
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