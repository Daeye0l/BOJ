package graph;

import java.io.*;
import java.util.*;

public class GRA14503 {
	static int n, m;
	static int[][] room;
	// 북(0), 동(1), 남(2), 서(3) 방향으로 이동하기 위한 배열
	static int[][] move = {{-1,0}, {0,1}, {1,0}, {0,-1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		room = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(r, c, d));
	}
	
	private static int bfs(int r, int c, int d) {
		Queue<Node> que = new LinkedList<>();
		int cnt = 1; // 처음 로봇 청소기의 위치 청소
		
		room[r][c] = 2;
		que.offer(new Node(r, c, d));
		while(!que.isEmpty()) {
			Node curr = que.poll();
			
			// 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 있는지 확인
			boolean clean = true;
			int rotate = 0;
			while(rotate < 4) {
				int nd = (curr.d+3)%4; // 현재 방향의 왼쪽 방향
				// 현재 위치에서 반시계 방향으로 90도 회전하여 한 칸 전진한 위치
				int nx = curr.x + move[nd][0];
				int ny = curr.y + move[nd][1];
				// 새로운 위치가 청소되지 않은 구역이라면 큐에 삽입
				if(room[nx][ny] == 0) {
					cnt++;
					room[nx][ny] = 2;
					que.offer(new Node(nx, ny, nd));
					clean = false;
					break;
				}
				curr.d = nd;
				rotate++;
			}
			
			// 현재 칸 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
			if(clean) {
				// 현재 위치에서 뒤로 한 칸 이동한 위치
				int nx = curr.x + move[(curr.d+2)%4][0];
				int ny = curr.y + move[(curr.d+2)%4][1];
				// 벽이 아니어서 뒤로 이동할 수 있다면 큐에 삽입
				if(room[nx][ny] != 1) {
					que.offer(new Node(nx, ny, curr.d));
				}
				// 뒤로 이동할 수 없다면 작동 중지
				else break;
			}
		}
		return cnt;
	}
	
	private static class Node {
		private int x;
		private int y;
		private int d;
		
		Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}