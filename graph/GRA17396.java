package graph;

import java.io.*;
import java.util.*;

public class GRA17396 {
	static int N;
	static long INF = 10000000000L;
	static List<List<Node>> graph = new ArrayList<>();
	static long[] distance;
	static int[] visibleCheck;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		visibleCheck = new int[N];
		for(int i = 0; i < N; i++) {
			visibleCheck[i] = Integer.parseInt(st.nextToken());
		}
		
		distance = new long[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
			distance[i] = INF;
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, t));
			graph.get(b).add(new Node(a, t));
		}
		
		dijkstra(0);
		
		if(distance[N-1] == INF) System.out.println(-1);
		else System.out.println(distance[N-1]);
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pqueue = new PriorityQueue<>();
		
		pqueue.offer(new Node(start, 0));
		distance[start] = 0;
		while(!pqueue.isEmpty()) {
			Node curr = pqueue.poll();	
			if(visited[curr.num]) continue;
			visited[curr.num] = true;
			
			for(Node next : graph.get(curr.num)) {
				if(next.num <= N-2 && visibleCheck[next.num] == 1) continue;
				
				if(distance[curr.num] + next.time < distance[next.num]) {
					distance[next.num] = distance[curr.num] + next.time;
					pqueue.offer(new Node(next.num, distance[next.num]));
				}
			}
		}
	}
	
	private static class Node implements Comparable<Node>{
		private int num;
		private long time;
		
		Node(int num, long time) {
			this.num = num;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return (int)(this.time - o.time);
		}
	}
}