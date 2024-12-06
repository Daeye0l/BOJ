package graph;

import java.io.*;
import java.util.*;

public class GRA11779 {
	static int n, m;
	static int[] path, distance;
	static List<List<Node>> connect = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		distance = new int[n+1];
		path = new int[n+1];
		
		for(int i = 0; i <= n; i++) {
			connect.add(new ArrayList<>());
			distance[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			connect.get(a).add(new Node(b, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		StringBuilder sb = new StringBuilder();
		sb.append(distance[end]).append("\n");
		Stack<Integer> result = pathTracking(start, end);
		sb.append(result.size()).append("\n");
		
		while(!result.isEmpty()) {
			sb.append(result.pop()).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static void dijkstra(int start) {
		// 출발점에서 노드까지 드는 비용이 적은 순서대로 정렬
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		
		distance[start] = 0;
		que.offer(new Node(start, 0));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			// 출발점에서 현재 노드까지의 거리가 기록한 최소 거리보다 크다면 건너뜀
			if(distance[curr.num] < curr.cost) continue;
			for(Node next : connect.get(curr.num)) {
				// 현재노드를 거쳐 다음 노드를 가는 것이 더 적은 비용이 든다면
				if(distance[curr.num] + next.cost < distance[next.num]) {
					// 다음 노드의 비용을 갱신하고 이전 노드가 무엇인지 기록 후 큐에 삽입
					distance[next.num] = distance[curr.num] + next.cost;
					path[next.num] = curr.num;
					que.offer(new Node(next.num, distance[next.num]));
				}
			}
		}
	}
	
	private static Stack<Integer> pathTracking(int start, int end) {
		Stack<Integer> stack = new Stack<>();
		int prev = end;
		
		// 종점부터 시작점까지 역추적해서 스택에 추가
		while(prev != start) {
			stack.push(prev);
			prev = path[prev];
		}
		stack.push(start);
		
		return stack;
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