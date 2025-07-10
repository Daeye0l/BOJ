package implementation;

import java.io.*;
import java.util.*;

public class IMPL17779 {
	static int N;
	static int result = Integer.MAX_VALUE;
	static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// x와 y가 동시에 0인 경우는 불가능하지만 각각의 최솟값은 0이 맞음
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				// 문제에 쓰인 d1과 d2는 1이상, d1과 d2가 모두 1이상이기 위해서 각각의 최댓값은 N-1
				for(int d1 = 1; d1 < N-1; d1++) {
					for(int d2 = 1; d2 < N-1; d2++) {
						// 경계를 세웟을 때 격자 범위를 벗어나는 경우를 제외
						if(x+d1+d2 >= N) continue;
						if(y-d1 < 0 || y+d2 >= N) continue;
						
						int diff = getDiff(seperate(x, y, d1, d2));
						result = Math.min(result, diff);
					}
				}
			}
		}
		
		System.out.println(result);
	}

	private static int[][] seperate(int x, int y, int d1, int d2) {
		int[][] seperateGrid = new int[N][N];
		
		// 1번 경계선
		for(int i = 0; i < d1; i++) {
			seperateGrid[y-i][x+i] = 5;
			seperateGrid[y+d2-i][x+d2+i] = 5;
		}
		// 2번, 3번 경계선
		for(int i = 0; i <= d2; i++) {
			seperateGrid[y+i][x+i] = 5;
			seperateGrid[y-d1+i][x+d1+i] = 5;
		}
		// 5번 선거구를 제외한 나머지 선거구들끼리의 경계선
		for(int r = 0; r < y-d1; r++) seperateGrid[r][x+d1] = 1;
		for(int c = N-1; c > x+d1+d2; c--) seperateGrid[y-d1+d2][c] = 2;
		for(int c = 0; c < x; c++) seperateGrid[y][c] = 3;
		for(int r = N-1; r > y+d2; r--) seperateGrid[r][x+d2] = 4;
		
		// 1번 선거구 색칠
		for(int r = 0; r < y; r++) {
			int c = 0;
			while(seperateGrid[r][c] == 0) {
				seperateGrid[r][c] = 1;
				c++;
			}
		}
		// 2번 선거구 색칠
		for(int r = 0; r < y-d1+d2; r++) {
			int c = N-1;
			while(seperateGrid[r][c] == 0) {
				seperateGrid[r][c] = 2;
				c--;
			}
		}
		// 3번 선거구 색칠
		for(int r = y+1; r < N; r++) {
			int c = 0;
			while(seperateGrid[r][c] == 0) {
				seperateGrid[r][c] = 3;
				c++;
			}
		}
		// 4번 선거구 색칠
		for(int r = y-d1+d2+1; r < N; r++) {
			int c = N-1;
			while(seperateGrid[r][c] == 0) {
				seperateGrid[r][c] = 4;
				c--;
			}
		}
		// 5번 선거구 색칠
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(seperateGrid[r][c] == 0) {
					seperateGrid[r][c] = 5;
				}
			}
		}
		
		return seperateGrid;
	}
	
	private static int getDiff(int[][] seperateGrid) {
		int[] populations = new int[5];
		
		// 각 선거구의 인구수를 저장
		for(int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				populations[seperateGrid[r][c]-1] += grid[r][c];
			}
		}
		
		// 가장 많은 인구수와 가장 적은 인구수 저장
		int max = 0;
		int min = Integer.MAX_VALUE;
		for(int population : populations) {
			max = Math.max(max, population);
			min = Math.min(min, population);
		}

		return max-min;
	}
}