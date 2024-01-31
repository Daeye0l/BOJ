package graph;

import java.io.*;
import java.util.*;

public class GRA2468 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1]; // 영역을 저장할 배열
		visited = new boolean[N+1][N+1]; // 영역 방문여부를 저장할 배열
		int max_height = 0; // 영역들 중 가장 높은 높이
		int max_safe_area = 0; // 안전영역의 최대 개수
		
		// 영역들의 높이를 입력받아서 영역을 생성후 저장하고 max_height 갱신
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int height = Integer.parseInt(st.nextToken());
				map[i][j] = height;
				max_height = Math.max(max_height, height);
			}
		}
		
		// 건물의 모든 높이에 대해 잠기는 건물을 확인하며 dfs
		for(int h = 0 ; h <= max_height; h++) {
			visited = new boolean[N+1][N+1];
			int safe_area = 0;
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(!visited[i][j] && map[i][j] > h) {
						dfs(i, j, h);
						safe_area++;
					}
				}
			}
			max_safe_area = Math.max(max_safe_area, safe_area);
		}
		
		System.out.println(max_safe_area);
	}
	
	private static void dfs(int x, int y, int h) {
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = x + move[i][0];
			int ny = y + move[i][1];
			if(nx >= 1 && nx <= N &&
			   ny >= 1 && ny <= N &&
			   !visited[nx][ny] &&
			   map[nx][ny] > h) {
			    visited[nx][ny] = true;
				dfs(nx, ny, h);
			}
		}
	}
}