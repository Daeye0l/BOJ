package graph;

import java.io.*;
import java.util.*;

public class GRA14940 {
	static int n, m, startX, startY;
	static int[][] map;
	static boolean[][] visited;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					startX = i;
					startY = j;
					map[i][j] = 0;
				}
			}
		}
		
		bfs(startX, startY);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j] && map[i][j] == 1) {
					sb.append(-1).append(" ");
				}
				else {
					sb.append(map[i][j]).append(" ");	 
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		visited = new boolean[n][m];
		
		que.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			for(int i = 0; i < move.length; i++) {
				int nx = curr[0] + move[i][0];
				int ny = curr[1] + move[i][1];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m
				   && !visited[nx][ny] && map[nx][ny] == 1) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					map[nx][ny] = map[curr[0]][curr[1]]+1;
				}
			}
		}
	}
}