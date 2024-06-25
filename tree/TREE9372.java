package tree;

import java.io.*;
import java.util.*;

public class TREE9372 {
	static List<List<Integer>> tree;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			visited = new boolean[n+1]; // 방문한 국가를 표시할 배열
			
			// 각 국가마다 하나씩의 리스트를 가짐
			tree = new ArrayList<>();
			for(int j = 0; j < n+1; j++) {
				tree.add(new ArrayList<>());
			}
			
			// 비행기 종류를 입력 받고 왕복하는 국가를 양방향 연관
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				tree.get(a).add(b);
				tree.get(b).add(a);
			}
			
			// 임의의 국가에서 출발하여 카운트
			dfs(1);
			System.out.println(cnt);
			cnt = 0;
		}
	}
	
	private static void dfs(int root) {
		visited[root] = true;
		for(int n : tree.get(root)) {
			// 방문하지 않았던 국가를 방문할 때만 카운트 증가
			if(!visited[n]) {
				cnt++;
				dfs(n);
			}
		}
	}
}