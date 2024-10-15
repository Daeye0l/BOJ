package graph;

import java.io.*;

public class GRA10026 {
	static int n;
	static char[][] originGrid, colorBlindnessGrid;
	static int[][] move = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		originGrid = new char[n][n];
		colorBlindnessGrid = new char[n][n];
		
		// 그리드 입력 받기
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < n; j++) {
				char color = s.charAt(j);
				originGrid[i][j] = color;
				// 적록색약인 사람은 적색과 녹색을 구부하지 못하므로 녹색인 경우도 적색으로 저장
				if(color == 'G') colorBlindnessGrid[i][j] = 'R';
				else colorBlindnessGrid[i][j] = color;
			}
		}
		
		System.out.println(nonColorBlindness());
		System.out.println(colorBlindness());
	}
	
	// 적록색약이 아닌 사람이 봤을 때의 구역 수를 반환
	private static int nonColorBlindness() {
		int cnt = 0;
		boolean[][] visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					dfs(i, j, originGrid, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// 적록색약인 사람이 봤을 때의 구역 수를 반환
	private static int colorBlindness() {
		int cnt = 0;
		boolean[][] visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!visited[i][j]) {
					dfs(i, j, colorBlindnessGrid, visited);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static void dfs(int x, int y, char[][] grid, boolean[][] visited) {
		char color = grid[x][y];
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = x + move[i][0];
			int ny = y + move[i][1];
			// 인접한 칸 중에서 현재 칸과 같은 색인 경우 깊이 우선 탐색
			if(nx >= 0 && nx < n && ny >= 0 && ny < n &&
			   !visited[nx][ny] && grid[nx][ny] == color) {
				dfs(nx, ny, grid, visited);
			}
		}
	}
}