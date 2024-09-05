package backTracking;

import java.io.*;
import java.util.*;

public class BT15650 {
	static int n, m;
	static boolean[] visited;
	static int[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		list = new int[m];
		
		dfs(0, 1);
	}
	
	private static void dfs(int depth, int num) {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				System.out.print(list[i] + " ");
			}

			System.out.println();
			return;
		}
		
		for(int i = num; i <= n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				list[depth] = i;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
	}
}