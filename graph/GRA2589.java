package graph;

import java.io.*;
import java.util.*;

public class GRA2589 {
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		List<int[]> lands = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'L') {
					lands.add(new int[] {i, j, 0});
				}
			}
 		}
		
		int result = 0;
		for(int[] land : lands) {
			int max_time = bfs(N, M, map, land);
			result = Math.max(result, max_time);
		}
		
		System.out.println(result);
	}
	
	private static int bfs(int N, int M, char[][] map, int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		int max_time = 0;
		
		visited[start[0]][start[1]] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			max_time = Math.max(max_time, curr[2]);
			
			for(int[] coord : coords) {
				int new_n = curr[0] + coord[0];
				int new_m = curr[1] + coord[1];
				
				if(new_n >= 0 && new_n < N && new_m >= 0 && new_m < M
				   && map[new_n][new_m] == 'L' && !visited[new_n][new_m]) {
					visited[new_n][new_m] = true;
					queue.offer(new int[] {new_n, new_m, curr[2]+1});
				}
			}
		}
		
		return max_time;
	}
}