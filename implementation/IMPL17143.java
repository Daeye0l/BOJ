package implementation;

import java.io.*;
import java.util.*;

public class IMPL17143 {
	static int R, C;
	static int sum = 0;
	static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		Shark[][] grid = new Shark[R+1][C+1];
		
		int M = Integer.parseInt(st.nextToken());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			grid[r][c] = new Shark(r, c, s, d, z);
		}
		
		for(int c = 1; c <= C; c++) {
			hunt(grid, c);
			grid = move(grid);
		}
		
		System.out.println(sum);
	}
	
	private static void hunt(Shark[][] grid, int c) {
		// 낚시왕이 서있는 열에서 가장 가까운 상어를 잡고 크기를 누적
		for(int r = 1; r <= R; r++) {
			if(grid[r][c] != null) {
				sum += grid[r][c].z;
				grid[r][c] = null;
				break;
			}
		}
	}
	
	private static Shark[][] move(Shark[][] grid) {
		Shark[][] newGrid = new Shark[R+1][C+1];
		
		// 상어를 크기순으로 정렬
		PriorityQueue<Shark> que = new PriorityQueue<>(new Comparator<>() {
			public int compare(Shark s1, Shark s2) {
				return s2.z - s1.z;
			}
		});
		
		// 상어들의 위치를 움직이고 우선순위 큐에 삽입
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				if(grid[r][c] != null) {
					Shark shark = grid[r][c];
					sharkMoves(shark);
					que.offer(shark);
				}
			}
		}
		
		// 우선순위 큐가 크기순으로 정렬이 되었기 때문에 같은 자리에 이미 상어가 있다면 건너뜀 
		while(!que.isEmpty()) {
			Shark shark = que.poll();
			if(newGrid[shark.r][shark.c] == null) {
				newGrid[shark.r][shark.c]= shark; 
			}
		}
		
		return newGrid;
	}
	
	private static void sharkMoves(Shark shark) {
		// 현재 상어의 위치
		int r = shark.r;
		int c = shark.c;
		
		if(shark.d == 1 || shark.d == 2) {
			shark.s %= 2*(R-1);
		}
		else shark.s %= 2*(C-1);
		
		for(int s = 0; s < shark.s; s++) {
			int nr = r + dirs[shark.d-1][0];
			int nc = c + dirs[shark.d-1][1];
			
			// 이동하려고 하는 칸이 격자판의 경계를 넘는 경우 방향을 반대로 바꿔서 이동
			if(!(nr >= 1 && nr <= R && nc >= 1 && nc <= C)) {
				if(shark.d == 1) shark.d = 2;
				else if(shark.d == 2) shark.d = 1;
				else if(shark.d == 3) shark.d = 4;
				else shark.d = 3;
				
				nr = r + dirs[shark.d-1][0];
				nc = c + dirs[shark.d-1][1];
			}

			r = nr;
			c = nc;
		}
		
		// 이동이 끝나면 끝난 위치로 변경
		shark.r = r;
		shark.c = c;
	}
	
	private static class Shark {
		private int r;
		private int c;
		private int s;
		private int d;
		private int z;
		
		Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
}