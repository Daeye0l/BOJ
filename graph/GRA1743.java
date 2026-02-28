package graph;

import java.io.*;
import java.util.*;

public class GRA1743 {
	static boolean[][] map, visited;
	static List<int[]> trashes = new ArrayList<>();
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			map[r][c] = true;
			trashes.add(new int[] {r, c});
		}
		
		int max = 0;
		for(int[] trash : trashes) {
			if(!visited[trash[0]][trash[1]]) {
				int size = bfs(N, M, trash);
				max = Math.max(max, size);
			}
		}
		
		System.out.println(max);
	}
	
	private static int bfs(int N, int M, int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		int size = 0;
		
		queue.offer(start);
		visited[start[0]][start[1]] = true;
		size++;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int[] coord : coords) {
				int new_r = curr[0] + coord[0];
				int new_c = curr[1] + coord[1];
				
				if(new_r >= 1 && new_r <= N && new_c >= 1 && new_c <= M
				   && !visited[new_r][new_c] && map[new_r][new_c]) {
					queue.offer(new int[] {new_r, new_c});
					visited[new_r][new_c] = true;
					size++;
				}
			}
		}
		
		return size;
	}
}