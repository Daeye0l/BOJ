package tree;

import java.io.*;
import java.util.*;

public class TREE14267 {
	static int n, m;
	static List<List<Integer>> child;
	static int[]score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		child = new ArrayList<>();
		score = new int [n+1];
		
		for(int i = 0; i <= n; i++) child.add(new ArrayList<>());
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num != -1) {
				child.get(num).add(i);
			}
		}
		
		for(int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			score[i] += w;
		}
		
		dfs(1);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(score[i]).append(" ");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int curr) {
		// 현재 직원의 부하 직원에 대해서
		for(int child : child.get(curr)) {
			// 부하 직원의 칭찬 수치에 자신의 칭찬 수치를 누적하고 재귀
			score[child] += score[curr];
			dfs(child);
		}
	}
}