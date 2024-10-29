package graph;

import java.io.*;
import java.util.*;

public class GRA17070 {
	static int n;
	static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		grid = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(grid[n][n] == 1) System.out.println(0);
		else System.out.println(bfs(1, 2, 0));
	}
	
	private static int bfs(int x, int y, int state) {
		Queue<Node> que = new LinkedList<>();
		/* bfs로 구현할 시 3차원 visited 배열을 써봤지만 (n, n)에 도달하는 각각의 상태(가로, 세로, 대각)가
		   하나씩만 존재한다는 보장이 visited를 빼야 함, 또한 파이프의 이동 방향은 역으로 거스를 수 없는 단방향임 */
		int cnt = 0;
		
		que.add(new Node(x, y, state));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			if(curr.x == n && curr.y == n) {
				cnt++;
				continue;
			}
			// 파이프의 상태가 가로인 경우
			if(curr.state == 0) {
				// 가로 이동과 대각 이동이 가능
				rowMove(curr, que);
				diagMove(curr, que);
			}
			// 파이프의 상태가 세로인 경우
			else if(curr.state == 1) {
				// 세로 이동과 대각 이동이 가능
				colMove(curr, que);
				diagMove(curr, que);
			}
			// 파이프에 상태가 대각선인 경우
			else {
				// 모든 이동이 가능
				rowMove(curr, que);
				colMove(curr, que);
				diagMove(curr, que);
			}
		}
		
		return cnt;
	}
	
	// 가로 이동
	private static void rowMove(Node curr, Queue<Node> que) {
		if(curr.y+1 <= n && grid[curr.x][curr.y+1] != 1) {
			//visited[curr.x][curr.y+1][0] = true;
			que.offer(new Node(curr.x, curr.y+1, 0));
		}
	}
	
	// 세로 이동
	private static void colMove(Node curr, Queue<Node> que) {
		if(curr.x+1 <= n && grid[curr.x+1][curr.y] != 1) {
			//visited[curr.x+1][curr.y][1] = true;
			que.offer(new Node(curr.x+1, curr.y, 1));
		}
	}
	
	// 대각 이동
	private static void diagMove(Node curr, Queue<Node> que) {
		if(curr.x+1 <= n && curr.y+1 <= n && grid[curr.x][curr.y+1] != 1
		   && grid[curr.x+1][curr.y] != 1 && grid[curr.x+1][curr.y+1] != 1) {
			que.offer(new Node(curr.x+1, curr.y+1, 2));
		}
	}
	
	private static class Node {
		private int x;
		private int y;
		private int state;
		
		Node(int x, int y, int state) {
			this.x = x;
			this.y = y;
			this.state = state;
		}
	}
}