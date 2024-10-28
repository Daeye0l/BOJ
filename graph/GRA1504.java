package graph;

import java.io.*;
import java.util.*;

public class GRA1504 {
	static int N, E, v1, v2;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int[] distV1 = dijkstra(v1);
		int[] distV2 = dijkstra(v2);
		
		// 1 -> v1 -> v2 -> N
		int path1 = distV1[1] + distV2[v1] + distV2[N];
		// 1 -> v2 -> v1 -> N
		int path2 = distV2[1] + distV1[v2] + distV1[N];
		
		if(path1 >= 200000000 && path2 >= 200000000) {
			System.out.println(-1);
		}
		else System.out.println(Math.min(path1, path2));
	}
	
	private static int[] dijkstra(int start) {
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		for(int i = 1; i <= N; i++) distance[i] = 200000000;

		distance[start] = 0;
		que.offer(new Node(start, 0));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			if(visited[curr.num]) continue;
			visited[curr.num] = true;
			for(Node neighbor : graph.get(curr.num)) {
				if(!visited[neighbor.num] && curr.cost + neighbor.cost < distance[neighbor.num]) {
					distance[neighbor.num] = curr.cost + neighbor.cost;
					que.offer(new Node(neighbor.num, distance[neighbor.num]));
				}
			}
		}
		
		return distance;
	}
	
	private static class Node {
		private int num;
		private int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
}
