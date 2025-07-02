package dp;

import java.io.*;
import java.util.*;

public class DP1937 {
	static int n;
	static int[][] forest;
	static int[][] dp;
	static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		forest = new int[n+1][n+1];
		dp = new int[n+1][n+1]; // (x, y) 위치에 도달하기 위한 최대 칸수
		
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				max = Math.max(max, dfs(i, j));	
			}
		}
		
		System.out.println(max);
	}
	
	private static int dfs(int x, int y) {
		// 한 번 값이 갱신된 적이 있으면 저장된 값 반환
		if(dp[x][y] != 0) {
			return dp[x][y];
		}
		
		// 현재 위치에 도달하기 위한 최대 칸은 1
		dp[x][y] = 1;
		
		// 인접한 방향이 범위 내에 있고 현재 대나무 양보다 더 많은 경우 재귀 호출
		for(int i = 0; i < dirs.length; i++) {
			int nx = x + dirs[i][0];
			int ny = y + dirs[i][1];

			if(nx >= 1 && nx <= n && ny >= 0 && ny <= n
			   && forest[nx][ny] > forest[x][y]) {
				dp[x][y] = Math.max(dp[x][y], dfs(nx, ny)+1);
			}
		}
		
		// 재귀 호출이 끝나면 현재 위치에 도달하귀 위한 최대 칸수 반환
		return dp[x][y];
	}
}