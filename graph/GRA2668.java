package graph;

import java.io.*;
import java.util.*;

public class GRA2668 {
	static int[] arr;
	static boolean[] visited;
	static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			visited[i] = true;
			dfs(i, i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result.size()).append('\n');
		for(int r : result) {
			sb.append(r).append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int curr, int start) {
		if(arr[curr] == start) {
			result.add(start);
		}
		
		if(!visited[arr[curr]]) {
			visited[arr[curr]] = true;
			dfs(arr[curr], start);
			visited[arr[curr]] = false;
		}
	}
}