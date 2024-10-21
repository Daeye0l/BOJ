package graph;

import java.io.*;
import java.util.*;

public class GRA13549 {
	static int n, k, min;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		visited = new boolean[100001];
		
		bfs();
		System.out.println(min);
	}
	
	private static void bfs() {
		Queue<Position> que = new LinkedList<>();
	
		que.offer(new Position(n, 0)); // 수빈이의 위치 우선순위 큐에 삽입
		while(!que.isEmpty()) {
			Position curr = que.poll();
			visited[curr.num] = true;
			// 순간이동이 존재하므로 목표지점에 도착했다고 해서 최소값이라는 보장이 없으므로 큐가 빌 때까지 계속해야함.
			if(curr.num == k) {
				min = Math.min(min, curr.cost);
			}
			// 순간이동 하는 경우는 비용이 추가 되지 않기 때문에 다른 두 경우보다 먼저 작성(큐에 삽입되는 순서가 탐색에 영향을 주기 때문)
			if(curr.num * 2 <= 100000 && !visited[curr.num * 2]) {
				que.offer(new Position(curr.num * 2, curr.cost));
			}
			if(curr.num + 1 <= 100000 && !visited[curr.num + 1]) {
				que.offer(new Position(curr.num + 1, curr.cost + 1));
			}
			if(curr.num - 1 >= 0 && !visited[curr.num - 1]) {
				que.offer(new Position(curr.num - 1, curr.cost + 1));
			}
		}
	}
	
	private static class Position {
		private int num;
		private int cost;
		
		Position(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
}