package tree;

import java.io.*;
import java.util.*;

public class TREE1240 {
	static int n;
	static List<List<Node>> connect;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		connect = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		
		// 두 노드의 연결 관계에 거리를 추가하여 저장
		for(int i = 0; i <= n; i++) connect.add(new ArrayList<>());
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			connect.get(a).add(new Node(b, d));
			connect.get(b).add(new Node(a, d));
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(getDistance(a, b)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int getDistance(int start, int end) {
		// 큐에 삽입되는 노드는 자기 자신의 번호와 시작 노드부터 자기 자신까지의 거리를 저장
		Queue<Node> que = new LinkedList<>();
		visited = new boolean[n+1];
		
		visited[start] = true; // 시작 노드 방문
		que.offer(new Node(start, 0)); // 시작 노드부터 시작 노드까지의 거리는 0
		while(!que.isEmpty()) {
			Node curr = que.poll();
			// 현재 노드가 도착 노드의 번호와 같다면 누적된 거리를 반환
			if(curr.num == end) return curr.distance;
			
			// 현재 노드와 연결된 노드 중 방문하지 않은 노드가 있다면 방문 처리 후
			// 거리를 누적하여 큐에 삽입
			for(Node next : connect.get(curr.num)) {
				if(!visited[next.num]) {
					visited[next.num] = true;
					que.offer(new Node(next.num, curr.distance + next.distance));
				}
			}
		}
		
		return -1;
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