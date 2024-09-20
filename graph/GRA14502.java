package graph;

import java.io.*;
import java.util.*;

public class GRA14502 {
	static int n, m, max;
	static int[][] lab;
	static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		max = 0;
		lab = new int[n][m];
		
		// 연구소 상태 입력 받기
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		buildWall(0);
		
		System.out.println(max);
	}
	
	private static void buildWall(int cnt) {
		// 벽을 3개 모두 세운 경우
		if(cnt == 3) {
			int[][] copyLab = copy(lab);
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					// 바이러스를 찾아서 퍼트리기
					if(copyLab[i][j] == 2) {
						virus(i, j, copyLab);
					}
				}
			}
			// 안전구역 계산하기
			int sum = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(copyLab[i][j] == 0) sum++;
				}
			}
			// 최대값 갱신
			max = Math.max(max, sum);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(lab[i][j] == 0) {
					lab[i][j] = 1; // 벽 세우기
					buildWall(cnt+1);
					lab[i][j] = 0; // 백 트래킹
				}
			}
		}
	}
	
	// 바이러스 퍼트리기
	private static void virus(int x, int y, int[][] lab) {
		for(int i = 0; i < 4; i++) {
			int nx = x + move[i][0];
			int ny = y + move[i][1];
			
			if(nx >= 0 && nx < n &&
			   ny >= 0 && ny < m &&
			   lab[nx][ny] == 0) {
				lab[nx][ny] = 2;
				virus(nx, ny, lab);
			}
		}
 	}
	
	private static int[][] copy(int[][] original) {
		int[][] temp = new int[n][m];
		for(int i = 0; i < n; i++) {
			temp[i] = original[i].clone();
		}
		return temp;
	}
}