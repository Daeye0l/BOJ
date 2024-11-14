package graph;

import java.io.*;
import java.util.*;

public class GRA2644 {
	static int start, end;
	static List<List<Integer>> graph;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		visited = new boolean[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		// 부모 번호와 자식 번호 연관짓기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(x).add(y);
			graph.get(y).add(x);
		}
		
		visited[start] = true;
		System.out.println(dfs(start, 0));
	}
	
	private static int dfs(int start, int cnt) {
		// 두 번호가 연관되어 있는 경우 촌수 반환
		if(start == end) return cnt;
		
		for(int next : graph.get(start)) {
			// 현재 번호와 연관된 번호 중 방문하지 번호가 있는 경우
			if(!visited[next]) {
				// 방문 후 dfs 수행
				visited[next] = true;
				int result = dfs(next, cnt+1);
				// dfs를 통해 촌수가 반환 되엇다면 촌수를 반환하고
				if(result != -1) return result;
				// 그렇지 않다면 백트래킹
				visited[next] = false;
			}
		}
		
		// 더 이상 방문할 수 있는 번호가 없는 경우 -1 반환 
		return -1;
	}
}