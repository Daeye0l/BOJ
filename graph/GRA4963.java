package graph;

import java.io.*;
import java.util.*;

public class GRA4963 {
	static int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1},
			               {1, -1}, {1, 1}, {-1, 1}, {-1, -1}}; // 상하좌우, 대각선 탐색을 위한 배열
	static int w, h, cnt;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) { // 입력이 0 0 이라면 종료
			StringTokenizer st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if(w == 0 && h == 0) return;
			
			map = new int[h][w];
			visited = new boolean[h][w];
			cnt = 0;
			
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1 && !visited[i][j]) { // dfs를 한 번 실행하여 연결요소를 찾을 때 마다 cnt += 1
						dfs(i, j);
						cnt += 1;
					}
				}
			}
			System.out.println(cnt);
		}
	}
		
	private static void dfs(int row, int col) {
		visited[row][col] = true;
		for(int i = 0; i < move.length; i++) {
			int nrow = row + move[i][0];
			int ncol = col + move[i][1];
			if(nrow >= 0 && nrow < h && ncol >= 0 && ncol < w &&
			   map[nrow][ncol] == 1 && !visited[nrow][ncol]) {
				dfs(nrow, ncol);
			}
		}
	}
}