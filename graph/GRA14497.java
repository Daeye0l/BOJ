package graph;

import java.io.*;
import java.util.*;

public class GRA14497 {
	static int N, M;
	static char[][] classRoom;
	static boolean[][] visited;
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		classRoom = new char[N+1][M+1];
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		int[] start = new int[] {x1, y1};
		int[] end = new int[] {x2, y2};
		
		
		for(int i = 1; i <= N; i++) {
			String line = br.readLine();
			for(int j = 1; j <= M; j++) {
				classRoom[i][j] = line.charAt(j-1);
			}
		}
		
		int cnt = 0;
		while(true) {
			cnt++;
			int result = bfs(start, end);
			if(result == 1) {
				System.out.println(cnt);
				return;
			}
		}
	}
	
	private static int bfs(int[] start, int[] end) {
		Queue<int[]> queue = new LinkedList<>();
		visited = new boolean[N+1][M+1];
		
		queue.offer(start);
		visited[start[0]][start[1]] = true;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			if(curr[0] == end[0] && curr[1] == end[1]) return 1;
			
			for(int[] coord : coords) {
				int new_n = curr[0] + coord[0];
				int new_m = curr[1] + coord[1];
				
				if(new_n > 0 && new_n <= N && new_m > 0 && new_m <= M
				   && !visited[new_n][new_m]) {
					visited[new_n][new_m] = true;
					if(classRoom[new_n][new_m] == '1') {
						classRoom[new_n][new_m] = '0';
					}
					else queue.offer(new int[] {new_n, new_m});
				}
			}
		}
		
		return 0;
	}
}