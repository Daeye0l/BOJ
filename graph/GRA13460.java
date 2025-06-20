package graph;

import java.io.*;
import java.util.*;

public class GRA13460 {
	static int n, m;
	static char[][] board;
	static int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		int rx = 0; int ry = 0;
		int bx = 0; int by = 0;
		for(int i = 0; i < n; i++) {
			String line = br.readLine();
			for(int j = 0; j < m; j++) {
				board[i][j] = line.charAt(j);
				// 빨간 구슬과 파란 구슬의 위치만 저장하고 이동할 수 있는 칸으로 변경
				if(board[i][j] == 'R') {
					rx = i; ry = j;
					board[i][j] = '.';
				}
				if(board[i][j] == 'B' ) {
					bx = i; by = j;
					board[i][j] = '.';
				}
			}
		}
		
		System.out.println(bfs(rx, ry, bx, by));
	}
	
	private static int bfs(int rx, int ry, int bx, int by) {
		Queue<Location> que = new LinkedList<>();
		boolean[][][][] visited = new boolean[n][m][n][m];
		
		Location start = new Location(rx, ry, bx, by, 0);
		que.offer(start);
		visited[rx][ry][bx][by] = true;
		
		while(!que.isEmpty()) {
			Location curr = que.poll();
			// 빨간 구슬만 구멍에 빠진 경우
			if(board[curr.rx][curr.ry] == 'O' && board[curr.bx][curr.by] != 'O') {
				if(curr.cnt <= 10) return curr.cnt;
			}
			
			// 보드를 상하좌우 기울여서 벽에 닿을 때까지 혹은 구멍에 들어갈 때까지 구슬을 이동
			for(int i = 0; i < 4; i++) {
				int newRx = curr.rx; int newRy = curr.ry;
				int newBx = curr.bx; int newBy = curr.by;
				
				// 빨간 구슬 이동
				while(board[newRx][newRy] == '.') {
					newRx += moves[i][0];
					newRy += moves[i][1];
				}
				if(board[newRx][newRy] == '#') {
					newRx -= moves[i][0];
					newRy -= moves[i][1];
				}
				
				// 파란 구슬 이동
				while(board[newBx][newBy] == '.') {
					newBx += moves[i][0];
					newBy += moves[i][1];
				}
				if(board[newBx][newBy] == '#') {
					newBx -= moves[i][0];
					newBy -= moves[i][1];
				}
				
				// 두 구슬의 위치가 겹치는 경우
				if(newRx == newBx && newRy == newBy && board[newRx][newRy] != 'O') {
					// 두 구슬이 방금 굴리기로 이동한 거리
					int redDist = Math.abs(newRx - curr.rx) + Math.abs(newRy - curr.ry);
					int blueDist = Math.abs(newBx - curr.bx) + Math.abs(newBy - curr.by);
					
					// 두 구슬 중에 더 많이 이동한 구슬을 한 칸 직전으로 돌려놔야 함
					if(redDist > blueDist) {
						newRx -= moves[i][0];
						newRy -= moves[i][1];
					}
					else {
						newBx -= moves[i][0];
						newBy -= moves[i][1];
					}
				}
				
				// 구슬의 이동이 끝나면 큐에 추가
				if(!visited[newRx][newRy][newBx][newBy]) {
					que.offer(new Location(newRx, newRy, newBx, newBy, curr.cnt+1));
					visited[newRx][newRy][newBx][newBy] = true;
				}
			}
		}
		return -1;
	}
	
	private static class Location{
		private int rx;
		private int ry;
		private int bx;
		private int by;
		private int cnt;
		
		Location(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
}