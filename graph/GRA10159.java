package graph;

import java.io.*;
import java.util.*;

public class GRA10159 {
	static List<List<Integer>> ascending_graph, descending_graph;
	static boolean[] visited;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		ascending_graph = new ArrayList<>();
		descending_graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			ascending_graph.add(new ArrayList<>());
			descending_graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			descending_graph.get(a).add(b);
			ascending_graph.get(b).add(a);
		}
		
		StringBuilder result = new StringBuilder();
		int heavy_cnt = 0;
		int light_cnt = 0;
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			cnt = 0;
			dfs(descending_graph, i);
			light_cnt = cnt-1;
			
			visited = new boolean[N+1];
			cnt = 0;
			dfs(ascending_graph, i);
			heavy_cnt = cnt-1;
			result.append((N-1)-light_cnt-heavy_cnt).append('\n');
		}
		System.out.println(result);
	}
	
	private static void dfs(List<List<Integer>> graph, int curr) {
		visited[curr] = true;
		cnt++;
		
		for(int next : graph.get(curr)) {
			if(!visited[next]) dfs(graph, next);
		}
	}
}