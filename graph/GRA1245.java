package graph;

import java.io.*;
import java.util.*;

public class GRA1245 {
	static int[][] adjs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static int N, M;
	static int[][] grid;
	static boolean[][] visited;
	static List<Peak> peakCandidates = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		grid = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int height = Integer.parseInt(st.nextToken());
				grid[i][j] = height;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!visited[i][j] && grid[i][j] != 0) {
					bfs(new int[] {i, j});	
				}
			}
		}
		
		int cnt = 0;
		for(Peak peakCandidate : peakCandidates) {
			if(isPeak(peakCandidate)) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	private static void bfs(int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		List<int[]> coords = new ArrayList<>();
		int targetHeight = grid[start[0]][start[1]];
		
		visited[start[0]][start[1]] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			coords.add(curr);
			
			for(int[] adj : adjs) {
				int new_n = curr[0] + adj[0];
				int new_m = curr[1] + adj[1];
				
				if(new_n >= 0 && new_n < N && new_m >= 0 && new_m < M
				   && !visited[new_n][new_m] && grid[new_n][new_m] != 0 && grid[new_n][new_m] == targetHeight) {
					visited[new_n][new_m] = true;
					queue.offer(new int[] {new_n, new_m});
				}
			}
		}
		
		peakCandidates.add(new Peak(coords));
	}
	
	private static boolean isPeak(Peak peak) {		
		for(int[] coord : peak.coords) {
			for(int[] adj : adjs) {
				int new_n = coord[0] + adj[0];
				int new_m = coord[1] + adj[1];
				
				if(new_n >= 0 && new_n < N && new_m >= 0 && new_m < M) {
					int peakHeight = grid[coord[0]][coord[1]];
					int adjHeight = grid[new_n][new_m];
					
					if(adjHeight > peakHeight) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private static class Peak {
		private List<int[]> coords;
		Peak(List<int[]> coords) {
			this.coords = coords;
		}
	}
}