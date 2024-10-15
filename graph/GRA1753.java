package graph;

import java.io.*;
import java.util.*;

public class GRA1753 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken()); // 정점 갯수
		int E = Integer.parseInt(st.nextToken()); // 간선 갯수
		int K = Integer.parseInt(br.readLine()); // 시작점
		List<List<Node>> graph = new ArrayList<>(); // 각 정점별로 연결된 정점 정보를 저장
		boolean[] visited = new boolean[V+1]; // 각 정점의 방문 여부를 저장
		int[] distance = new int[V+1]; // 시작점부터 각 정점까지의 최단 거리를 저장
	
		for(int i = 0; i <= V ; i++) {
			graph.add(new ArrayList<Node>());
		}
		// 간선 갯수만큼 반복해서 연결 정보 저장
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			// u 정점에서 v 정점으로 w의 가중치를 가지고 연결되어 있음
			graph.get(u).add(new Node(v, w));
		}
		
		// distance 배열 초기값을 무한대로 설정
		for(int i = 1; i <= V; i++) distance[i] = Integer.MAX_VALUE;
		// 시작점부터 해당 정점까지의 최단 거리가 짧은 순으로 큐 정렬
		Queue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		distance[K] = 0; // 시작점부터 시작점까지의 거리는 0
		que.add(new Node(K, 0));
		
		while(!que.isEmpty()) {
			Node curr = que.poll(); // 우선선위 큐에서 하나 poll
			if(!visited[curr.num]) visited[curr.num] = true; // 방문하지 않은 정점이라면 방문 처리
			// 현재 정점과 연결된 정점들을 순회
			for(Node next : graph.get(curr.num)) {
				// 현재 정점을 거쳐서 다음 정점으로 가는 것이 시작점에서 다음 정점으로 가는 거리보다 작다면
				if(!visited[next.num] && curr.cost + next.cost < distance[next.num]) {
					distance[next.num] = curr.cost + next.cost; // 거리 업데이트
					que.add(new Node(next.num, curr.cost + next.cost)); // 업데이트한 정점을 다시 우선순위 큐에 삽입
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= V; i++) {
			if(distance[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
			else sb.append(distance[i]).append("\n");
		}
		System.out.println(sb);
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