package graph;

import java.io.*;
import java.util.*;

public class GRA24479 {
	static List<List<Integer>> connections;
	static boolean[] visited;
	static int[] result;
	static int order = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			connections.get(u).add(v);
			connections.get(v).add(u);
		}
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(connections.get(i));
		}
		
		dfs(R);
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(result[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void init(int N) {
		connections = new ArrayList<>();
		visited = new boolean[N+1];
		result = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			connections.add(new ArrayList<>());
		}
	}
	
	private static void dfs(int R) {
		visited[R] = true;
		result[R] = order++;
		
		for(int next : connections.get(R)) {
			if(!visited[next]) {
				dfs(next);
			}
		}
	}
}