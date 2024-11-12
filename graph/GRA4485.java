package graph;

import java.io.*;
import java.util.*;

public class GRA4485 {
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int testCnt = 1;
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				System.out.println(sb);
				return;
			}
			
			// 각 칸의 도둑루피 크기를 저장
			int[][] cave = new int[n][n];
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			sb.append("Problem ").append(testCnt+": ")
			.append(dijkstra(n, cave)).append("\n");
			testCnt++;
		}
	}
	
	private static int dijkstra(int n, int[][] cave) {
		// 출발점에서 칸까지의 비용이 작은 순서대로 큐 정렬
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		
		boolean[][] visited = new boolean[n][n];
		// 출발점에서 각 칸까지의 비용을 무한대로 설정
		int[][] minCost = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				minCost[i][j] = Integer.MAX_VALUE;
			}
		}
		
		// 출발점 우선순위 큐에 삽입
		visited[0][0] = true;
		que.offer(new Node(0, 0, cave[0][0]));
		while(!que.isEmpty()) {
			Node curr = que.poll(); // 현재 확인하는 칸
			for(int i = 0; i < move.length; i++) {
				int nx = curr.x + move[i][0];
				int ny = curr.y + move[i][1];
				// 현재 칸에서 이동 가능한 다음 칸에 대해
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
					// 현재 칸을 거쳐가는 가는 것이 더 유리하다면
					if(curr.cost + cave[nx][ny] < minCost[nx][ny]) {
						// 다음 칸의 최소 비용을 갱신 후 큐에 추가
						minCost[nx][ny] = curr.cost + cave[nx][ny];
						visited[nx][ny] = true;
						que.offer(new Node(nx, ny, minCost[nx][ny]));
					}
				}
			}
		}
		
		return minCost[n-1][n-1];
	}
	
	// 칸의 위치와 출발점에서 칸까지의 최소 비용을 저장
	private static class Node {
		private int x;
		private int y;
		private int cost;
		
		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
}