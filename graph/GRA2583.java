package graph;

import java.io.*;
import java.util.*;

public class GRA2583 {
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	static List<Integer> areas = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[M][N];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int m1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int m2 = Integer.parseInt(st.nextToken());
			
			drawRectangle(n1, m1, n2, m2);
		}
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(M, N, new int[] {i, j});
				}
			}
		}
		
		Collections.sort(areas);
		StringBuilder sb = new StringBuilder();
		for(int area : areas) {
			sb.append(area).append(" ");
		}
		
		System.out.println(areas.size());
		System.out.println(sb);
	}
	
	private static void drawRectangle(int n1, int m1, int n2, int m2) {
		for(int i = m1; i < m2; i++) {
			for(int j = n1; j < n2; j++) {
				visited[i][j] = true;
			}
		}
	}
	
	private static void bfs(int M, int N, int[] start) {
		Queue<int[]> queue = new LinkedList<>();
		int area = 0;
		
		visited[start[0]][start[1]] = true;
		queue.offer(start);
		area += 1;
		
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			
			for(int i = 0; i < coords.length; i++) {
				int new_m = curr[0] + coords[i][0];
				int new_n = curr[1] + coords[i][1];
				
				if(new_m >=0 && new_m < M && new_n >= 0 && new_n < N && !visited[new_m][new_n]) {
					visited[new_m][new_n] = true;
					queue.offer(new int[] {new_m, new_n});
					area += 1;
				}
			}
		}
		
		areas.add(area);
	}
}