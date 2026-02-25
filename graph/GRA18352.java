package graph;

import java.io.*;
import java.util.*;

public class GRA18352 {
	static int INF = 300000;
	static int[] distances;
	static boolean[] visited;
	static List<List<Node>> connections;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			connections.get(A).add(new Node(B, 1));
		}
		
		dijkstra(X);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			if(distances[i] == 0 || distances[i] == INF) continue;
			if(distances[i] == K) sb.append(i).append("\n");
		}
		
		if(sb.length() != 0) System.out.println(sb);
		else System.out.println(-1);
	}
	
	private static void init(int N) {
		distances = new int[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			distances[i] = INF;
		}
		
		connections = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			connections.add(new ArrayList<>());
		}
	}
	
	private static void dijkstra(int start) {
		Queue<Node> queue = new PriorityQueue<>(new Comparator<>() {
			public int compare(Node n1, Node n2) {
				return n1.distance - n2.distance;
			}
		}); 
		
		distances[start] = 0;
		queue.add(new Node(start, 0));
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(!visited[curr.num]) {
				visited[curr.num] = true; 
			}
			
			for(Node next : connections.get(curr.num)) {
				if(!visited[next.num] && curr.distance + next.distance < distances[next.num]) {
					distances[next.num]= curr.distance + next.distance; 
					queue.offer(new Node(next.num, distances[next.num]));
				}
			}
		}
	}
	
	private static class Node {
		private int num;
		private int distance;
		
		Node(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
	}
}