package backTracking;

import java.io.*;
import java.util.*;

public class BT15652 {
	static int n, m;
	static int[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new int[m];
		
		dfs(0, 1);
	}
	
	private static void dfs(int depth, int num) {
		if(depth == m) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < m; i++) {
				sb.append(list[i] + " ");
			}
			System.out.println(sb);
			return;
		}
		for(int i = num; i <= n; i++) {
			list[depth] = i;
			dfs(depth + 1, i);
			
		}
	}
}