package dp;

import java.io.*;
import java.util.*;

public class DP11048 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] maze = new int[n+1][m+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				maze[i][j] = maze[i][j] +  Math.max(maze[i-1][j-1], Math.max(maze[i-1][j], maze[i][j-1]));
			}
		}
		
		System.out.println(maze[n][m]);
	}
}