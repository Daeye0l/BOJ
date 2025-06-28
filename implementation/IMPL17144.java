package implementation;

import java.io.*;
import java.util.*;

public class IMPL17144 {
	static int r, c;
	static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		// 격자 상태 입력받기
		int[][] grid = new int[r+1][c+1];
		List<int[]> cleaner = new ArrayList<>();
		for(int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= c; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if(grid[i][j] == -1) {
					cleaner.add(new int[] {i, j});
				}
			}
		}
		
		// t초 동안 확산과 공기청정기 작동을 반복
		for(int i = 0; i < t; i++) {
			spread(grid);
			cleanerWork(grid, cleaner);
		}
		
		// 남아있는 미세먼지의 양 구하기
		int result = 0;
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				if(grid[i][j] != -1) result += grid[i][j];
			}
 		}
		
		System.out.println(result);
	}
	
	private static void spread(int[][] grid) {
		// 미세먼지 확산 위치와 해당 확산량을 저장할 리스트
		List<int[]> list = new ArrayList<>();
		
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				// 미세먼지가 있는 칸인 경우
				if(grid[i][j] != 0 && grid[i][j] != -1) {
					// 확산되는 방향의 개수와 확산량을 저장
					int spreadCnt = 0;
					int spreadAmount = grid[i][j] / 5;
					
					// 인접한 칸이 격자 범위 내이면서 공기청정기 칸이 아닌 경우만 확산 정보 저장
					for(int[] dir : dirs) {
						int nr = i + dir[0];
						int nc = j + dir[1];						
						if(nr >= 1 && nr <= r && nc >= 1 && nc <= c
						   && grid[nr][nc] != -1) {
							spreadCnt += 1;
							list.add(new int[] {nr, nc, spreadAmount});
						}
					}
					
					// 남은 미세먼지 양으로 변경
					grid[i][j] -= spreadAmount * spreadCnt;
				}
			}
		}
		
		// 미세먼지 확산 동시에 진행
		for(int[] arr : list) {
			grid[arr[0]][arr[1]] += arr[2];
		}
	}
	
	private static void cleanerWork(int[][] grid, List<int[]> cleaner) {
		int[] topCleaner = cleaner.get(0);
		int[] bottomCleaner = cleaner.get(1);
		
		// 위쪽 공기청정기 작동
		int sr = 1; int er = topCleaner[0];
		int sc = 1; int ec = c;
		
		// 시작열에 있는 미세먼지 이동
		for(int i = er; i > sr; i--) {
			grid[i][sc] = grid[i-1][sc];
		}
		// 시작행에 있는 미세먼지 이동
		for(int i = sc; i < ec; i++) {
			grid[sr][i] = grid[sr][i+1];
		}
		// 끝열에 있는 미세먼지 이동
		for(int i = sr; i < er; i++) {
			grid[i][ec] = grid[i+1][ec];
		}
		// 끝행에 있는 미세먼지 이동
		for(int i = ec; i > sc+1; i--) {
			grid[er][i] = grid[er][i-1];
		}
		grid[topCleaner[0]][topCleaner[1]+1] = 0;
		
		grid[topCleaner[0]][topCleaner[1]] = -1;
		
			
		// 아래쪽 공기청정기 작동
		sr = bottomCleaner[0]; er = r;
		sc = 1; ec = c;
		
		// 시작열에 있는 미세먼지 이동
		for(int i = sr; i < er; i++) {
			grid[i][sc] = grid[i+1][sc];
		}
		// 끝행에 있는 미세먼지 이동
		for(int i = sc; i < ec; i++) {
			grid[er][i] = grid[er][i+1];
		}
		// 끝열에 있는 미세먼지 이동
		for(int i = er; i > sr; i--) {
			grid[i][ec] = grid[i-1][ec];
		}
		// 시작행에 있는 미세먼지 이동
		for(int i = ec; i > sc+1; i--) {
			grid[sr][i] = grid[sr][i-1];
		}
		grid[bottomCleaner[0]][bottomCleaner[1]+1] = 0;
		
		grid[bottomCleaner[0]][bottomCleaner[1]] = -1;
	}
}