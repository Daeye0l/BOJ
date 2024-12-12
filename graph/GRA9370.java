package graph;

import java.io.*;
import java.util.*;

public class GRA9370 {
	static int n;
	static List<List<Node>> connect;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for(int i = 0; i < T; i++) {
			// 교차로, 도로, 목적지 후보의 갯수
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			// 출발지, 듀오는 교차로 g와 h 사이에 있는 도로를 지나감
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int gToH = 0; // 교차로 g와 h를 잇는 도로의 길이를 저장
			
			// 교차로 a와 b 사이에 길이 d인 도로가 있음
			connect = new ArrayList<>();
			for(int j = 0; j <= n; j++) connect.add(new ArrayList<>());
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				if((a == g && b == h) || (a == h && b == g)) gToH = d;
				connect.get(a).add(new Node(b, d));
				connect.get(b).add(new Node(a, d));
			}
			
			// 목적지 후보를 입력 받아 저장
			int[] destination = new int[t];
			for(int j = 0; j < t; j++) {
				destination[j] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(destination);
			
			int[] dijkstraS = dijkstra(s);
			int[] dijkstraG = dijkstra(g);
			int[] dijkstraH = dijkstra(h);
			for(int e : destination) {
				int result1 = dijkstraS[e]; // s에서 목적지까지의 최단 경로
				int result2 = dijkstraS[g] + gToH + dijkstraH[e]; // s->g->h->목적지의 최단 경로
				int result3 = dijkstraS[h] + gToH + dijkstraG[e]; // s->h->g->목적지의 최단 경로
				// result1과 min(result2, result3)의 값이 같다면 무조건 g와 h사이의 도로를 지나가게 됨
				if(Math.min(result2,  result3) == result1) {
					sb.append(e).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int[] dijkstra(int start) {
		// 출발점에서 자신까지의 최단 거리가 작은 순서대로 정렬
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.distance - n2.distance;
			}
		});
		int[] distance = new int[n+1];
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		
		distance[start] = 0;
		que.offer(new Node(start, 0));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			if(curr.distance > distance[curr.num]) continue;
			for(Node next : connect.get(curr.num)) {
				// 현재 노드를 거쳐서 다음 노드를 가는 것이 다음 노드에게 더 이득인 경우 
				if(distance[curr.num] + next.distance < distance[next.num]) {
					distance[next.num] = distance[curr.num] + next.distance;
					que.offer(new Node(next.num, distance[next.num]));
				}
			}
		}
	
		return distance;
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