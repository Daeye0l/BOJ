package graph;

import java.io.*;
import java.util.*;

public class GRA3184 {
	static int[][] coords = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int R, C, o_cnt, v_cnt;
	static char[][] backyard;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		backyard = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				backyard[i][j] = line.charAt(j);
				if(backyard[i][j] == '#') {
					visited[i][j] = true;
				}
			}
		}
		
		int survived_o = 0;
		int survived_v = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(!visited[i][j]) {	
					o_cnt = 0; v_cnt = 0;
					dfs(new int[] {i, j});
					if(o_cnt > v_cnt) survived_o += o_cnt;
					else survived_v += v_cnt;
					o_cnt = 0; v_cnt = 0;
				}
			}
		}
		
		System.out.println(survived_o + " " + survived_v);
	}
	
	private static void dfs(int[] curr) {
		if(visited[curr[0]][curr[1]]) return;
		
		visited[curr[0]][curr[1]] = true;
		if(backyard[curr[0]][curr[1]] == 'o') o_cnt++;
		else if(backyard[curr[0]][curr[1]] == 'v') v_cnt++;
			
		for(int[] coord : coords) {
			int new_r = curr[0] + coord[0];
			int new_c = curr[1] + coord[1];
			
			if(new_r >= 0 && new_r < R && new_c >= 0 && new_c < C
			   && !visited[new_r][new_c]) {
				dfs(new int[] {new_r, new_c});
			}
		}
	}
}