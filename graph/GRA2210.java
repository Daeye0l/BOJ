package graph;

import java.io.*;
import java.util.*;

public class GRA2210 {
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] grid = new int[5][5];
	static Set<String> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		String temp = "";
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dfs(new int[] {i, j}, 0, temp);
				temp = "";
			}
		}
		
		System.out.println(set.size());
	}
	
	private static void dfs(int[] curr, int depth, String temp) {
		if(depth == 6) {
			set.add(temp);
			return;
		}
		
		temp += grid[curr[0]][curr[1]];
		for(int[] coord : coords) {
			int new_i = curr[0] + coord[0];
			int new_j = curr[1] + coord[1];
			
			if(new_i >= 0 && new_i < 5 && new_j >= 0 && new_j < 5) {
				dfs(new int[] {new_i, new_j}, depth+1, temp);
			}
		}
	}
}