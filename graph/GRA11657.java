package graph;

import java.io.*;
import java.util.*;

public class GRA11657 {
	static List<List<Node>> graph = new ArrayList<>();
	static long[] distance;
	static int INF = 5000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, c)); // 시작점, 도착점, 비용의 그래프 관계 저장
		}
		
		if(!bellmanFord(n, 1)) System.out.println(-1);
		else { // 
			for(int i = 2; i <= n; i++) {
				if(distance[i] == INF) System.out.println(-1);
				else System.out.println(distance[i]);
			}
		}
		
	}
	
	private static boolean bellmanFord(int n, int start) {
		distance = new long[n+1];
		for(int i = 1; i <= n; i++) {
			distance[i] = INF;
		}
		distance[start] = 0; // 시작점에서 시작점까지의 거리는 0
		
		for(int i = 0; i < n-1; i++) { // n-1번 반복(한 정점에서 다른 정점으로 이동하는 경로의 최대 간선 개수)
			for(int curr = 1; curr <= n; curr++) { // 모든 정점에 대해 반복
				for(Node next : graph.get(curr)) { // 최단거리 갱신
					if(distance[curr] != INF && distance[next.num] > distance[curr] + next.cost) {
						distance[next.num] = distance[curr] + next.cost;
					}
				}
			}
		}
		
		// n-1을 반복하고도 최단 거리가 갱신되려고 한다면 음의 사이클이 존재
		for(int curr = 1; curr <= n; curr++) {
			for(Node next : graph.get(curr)) {
				if(distance[curr] != INF && distance[next.num] > distance[curr] + next.cost) {
					 return false;
				}
			}
		}
		
		return true;
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