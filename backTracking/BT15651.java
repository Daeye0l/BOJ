package backTracking;

import java.io.*;
import java.util.*;

public class BT15651 {
	static int n, m;
	static int[] list;
	static BufferedWriter bw; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new int[m];
		
		dfs(0);
		bw.flush();
		bw.close();
	}
	
	private static void dfs(int depth) throws IOException {
		if(depth == m) {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < m; i++) {
				sb.append(list[i] + " ");
			}
			bw.write(sb.toString());
			bw.newLine();
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			// visited 배열 부분을 없애 방문했던 숫자도 다시 방문 가능하게 수정
			list[depth] = i;
			dfs(depth + 1);
		}
	}
}