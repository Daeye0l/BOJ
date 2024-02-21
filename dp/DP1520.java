package dp;

import java.io.*;
import java.util.*;

public class DP1520 {
	static int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
	static int m, n;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n]; // 각 지점의 높이를 저장할 지도
		dp = new int[m][n]; // i, j의 위치에서 m, n의 위치로 가는 경로의 개수
		
		// 지점들의 높이를 입력받아 저장하고 dp배열 -1로 초기화
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	private static int dfs(int row, int col) {
		if(row == m-1 && col == n-1) return 1; // 목적지에 도달했다면 1을 반환
		if(dp[row][col] == -1) { // 방문한 적이 없는 지점이라면
			dp[row][col] = 0; // 해당 위치의 dp값을 0으로 초기화
			for(int i = 0; i < 4; i++) { // 내리막길 탐색
				int nrow = row + move[i][0];
				int ncol = col + move[i][1];
				if(nrow >= 0 && nrow < m &&
				   ncol >= 0 && ncol < n &&
				   map[nrow][ncol] < map[row][col]) {
					dp[row][col] += dfs(nrow, ncol);
				}
			}	
		}
		return dp[row][col]; // 이미 방문한 위치에 도달하면 그 위치의 dp값 반환
	}
}