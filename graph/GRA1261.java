package graph;

import java.io.*;
import java.util.*;

public class GRA1261 {
	static int n, m;
	static int[][] maze;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		maze = new int[n+1][m+1];
		
		for(int i = 1; i <= n; i++) {
			String s = br.readLine();
			for(int j = 1; j <= m; j++) {
				maze[i][j] = s.charAt(j-1)-'0';
			}
		}
		
		System.out.println(bfs(1, 1));
	}
	
	private static int bfs(int x, int y) {
		// 부순 벽의 갯수가 적은 순서대로 정렬
		PriorityQueue<Node> que = new PriorityQueue<>(new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n1.breakCnt - n2.breakCnt;
			}
		});
		boolean[][] visited = new boolean[n+1][m+1];
		
		visited[x][y] = true;
		que.offer(new Node(x, y, 0));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			if(curr.x == n && curr.y == m) {
				return curr.breakCnt;
			}
			// 미로를 벗어나지 않는 범위 내로 이동
			for(int i = 0; i < move.length; i++) {
				int nx = curr.x + move[i][0];
				int ny = curr.y + move[i][1];
				if(nx >= 1 && nx <= n && ny >= 1 && ny <= m
				   && !visited[nx][ny]) {
					// 다음 이동할 칸이 벽이라면 현재까지 부순 갯수+1
					if(maze[nx][ny] == 1) {
						visited[nx][ny] = true;
						que.offer(new Node(nx, ny, curr.breakCnt+1));
					}
					// 벽이 아니라면 현재까지 부순 갯수 그대로 큐에 삽입
					else {
						visited[nx][ny] = true;
						que.offer(new Node(nx, ny, curr.breakCnt));
					}
				}
			}
		}
		
		return -1;
	}
	
	private static class Node {
		private int x;
		private int y;
		private int breakCnt;
		
		Node(int x, int y, int breakCnt) {
			this.x = x;
			this.y = y;
			this.breakCnt = breakCnt;
		}
	}
}