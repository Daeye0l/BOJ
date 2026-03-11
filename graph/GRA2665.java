package graph;

import java.io.*;
import java.util.*;

public class GRA2665 {
	static int n;
	static int INF = 2500;
	static int[][] grid, distance;
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		grid = new int[n+1][n+1];
		distance = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				distance[i][j] = INF;
			}
		}
		
		for(int i = 1; i <= n; i++) {
			String line = br.readLine();
			for(int j = 1; j <= n; j++) {
				grid[i][j] = Character.getNumericValue(line.charAt(j-1));
			}
		}
		
		dijkstra(new int[] {1, 1,});
		System.out.println(distance[n][n]);
	}
	
	private static void dijkstra(int[] start) {
		Deque<int[]> deque = new LinkedList<>();
		
		distance[start[0]][start[1]] = 0;
		deque.offerLast(start);
		
		while(!deque.isEmpty()) {
			int[] curr = deque.pollFirst();
			int x = curr[0];
			int y = curr[1];
			
			for(int[] coord : coords) {
				int nx = x + coord[0];
				int ny = y + coord[1];
				
				if(nx > 0 && nx <= n && ny > 0 && ny <= n) {
					int cost = 1;
					if(grid[nx][ny] == 1) cost = 0;
					
					if(distance[x][y] + cost < distance[nx][ny]) {
						distance[nx][ny] = distance[x][y] + cost;
						if(cost == 0) deque.offerFirst(new int[] {nx, ny});
						else deque.offerLast(new int[] {nx, ny});
					}
				}
			}
		}
	}
}