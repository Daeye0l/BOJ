package graph;

import java.io.*;
import java.util.*;

public class GRA1238 {
	static int n, m, x;
	static List<List<Node>> graph, revGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>(); // 시작 도시 -> 도착 도시 방향의 정방향 그래프
		revGraph = new ArrayList<>(); // 위의 그래프에서 모든 간선을 뒤집은 그래프
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			revGraph.add(new ArrayList<>());
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(s).add(new Node(e, c));
			revGraph.get(e).add(new Node(s, c));
		}
		
		int max = 0;
		/* x를 제외한 도시들에서 x로 가는 최단 거리를 구하기 위해 다익스트라를 여러번 수행할 수도 있지만
		   역으로 생각하면 간선을 뒤집은 상태에서 x에서 다른 모든 도시들로 1번의 다익스트라 수행 가능 */
		int[] distanceNToX = dijkstra(x, revGraph);
		int[] distanceXToN = dijkstra(x, graph);
		for(int i = 1; i <= n; i++) {
			// 어떤 한 도시에서 x로 가는 거리와 x에서 다시 처음 도시로 돌아오는 값을 더해서 최대 값을 저장  
			max = Math.max(max, distanceNToX[i] + distanceXToN[i]);
		}
		
		System.out.println(max);
	}
	
	private static int[] dijkstra(int start, List<List<Node>> graph) {
		// 시작점에서 해당 지점까지의 최단 거리가 작은 순서대로 정렬
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		int[] distance = new int[n+1]; // 시작점에서 다른 지점들까지의 최단 거리를 저장
		boolean[] visited = new boolean[n+1]; // 각 지점의 방문 여부를 저장
		
		for(int i = 1; i <= n; i++) distance[i] = 100000;
		distance[start] = 0;
		que.offer(new Node(start, 0));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			for(Node next : graph.get(curr.num)) {
				// 현재 지점을 거쳐가는 것이 더 작은 비용이 든다면 최단 거리 갱신 
				if(!visited[next.num] && curr.cost + next.cost < distance[next.num]) {
					distance[next.num] = curr.cost + next.cost;
					// 갱신된 지점 큐에 삽입
					que.offer(new Node(next.num, distance[next.num]));
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
