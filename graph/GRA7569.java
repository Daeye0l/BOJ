package graph;

import java.io.*;
import java.util.*;

public class GRA7569 {
	static int[][] move = {{0,-1,0}, {0,1,0}, {-1,0,0}, {1,0,0}, {0,0,1}, {0,0,-1}};
	static int M, N, H, allRipeCnt;
	static int[][][] box;
	static Queue<Grid> que;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		box = new int[N][M][H];
		que = new LinkedList<>();
		allRipeCnt = 0; // // 빈 칸을 제외하고 모든 토마토가 익었을 경우 갯수
		boolean isAllRipe = true; // 모든 토마토가 익었는지 여부를 저장
		
		for(int h = 0; h < H; h++) {
			for(int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for(int m = 0; m < M; m++) {
					int state = Integer.parseInt(st.nextToken()); 
					box[n][m][h] = state;
					if(state == 0) isAllRipe = false; // 안 익은 토마토가 존재한다면 false로 변경
					if(state != -1) allRipeCnt++; // 빈 칸이 아닌 모든 칸을 누적
					if(state == 1) que.offer(new Grid(n, m, h)); // 처음에 익은 토마토들을 큐에 저장
				}
			}
		}
		
		if(isAllRipe) System.out.println(0); // 모든 토마토가 익어있는 상태인 경우
		else System.out.println(tomato());
	}
	
	private static int tomato() {
		int days = 0;
		int ripeCnt = que.size(); // 익은 토마토의 초기값은 처음 익은 토마토의 갯수
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) {
				Grid curr = que.poll(); // 큐에서 하나 offer
				for(int j = 0; j < 6; j++) {
					int nx = curr.x + move[j][0];
					int ny = curr.y + move[j][1];
					int nz = curr.z + move[j][2];
					if(isPossible(nx, ny, nz)) { // 상,하,좌,우,전,후 중 이동이 가능하다면
						box[nx][ny][nz] = 1; // 익은 토마토로 변경
						ripeCnt++;
						que.offer(new Grid(nx, ny, nz)); // 큐에 삽입
					}
				}	
			}
			days += 1; // 하나의 익은 토마토에 대해 인접한 안 익은 토마토를 모두 처리한 후 카운트 증가
		}

		if(ripeCnt == allRipeCnt) return days-1; // 모든 토마토가 익은 경우 최소 일수를 반환
		else return -1;
	}
	
	// 다음 좌표로 이동 가능한지 여부를 반환
	private static boolean isPossible(int x, int y, int z) {
		if(x >= 0 && x < N && y >= 0 && y < M && z >= 0 && z < H && box[x][y][z] == 0) {
			return true;
		}
		return false;
	}
	
	private static class Grid {
		private int x;
		private int y;
		private int z;
		
		Grid(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}