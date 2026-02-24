package graph;

import java.io.*;
import java.util.*;

public class GRA1926 {
	static int[][] canvas;
	static boolean[][] visited;
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); 
		int m = Integer.parseInt(st.nextToken());
		canvas = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				canvas[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;
		int max_area = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j] && canvas[i][j] == 1) {
					max_area = Math.max(max_area, bfs(n, m, new int[] {i, j}));
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(max_area);
	}
	
	private static int bfs(int n, int m, int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		int area = 0;
		
		visited[start[0]][start[1]] = true;
		queue.offer(start);
		area++;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int[] coord : coords) {
				int new_n = curr[0] + coord[0];
				int new_m = curr[1] + coord[1];
				
				if(isPossible(n, m, new_n, new_m)) {
					visited[new_n][new_m] = true;
					queue.offer(new int[] {new_n, new_m});
					area++;
				}
			}
		}
		
		return area;
	}
	
	private static boolean isPossible(int n, int m, int new_n, int new_m) {
		if(new_n < 0 || new_m < 0) return false;
		else if (new_n >= n || new_m >= m) return false;
		else if(visited[new_n][new_m] || canvas[new_n][new_m] == 0) return false;
		return true;
		
	}
}