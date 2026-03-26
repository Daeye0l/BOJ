package graph;

import java.io.*;
import java.util.*;

public class GRA1506 {
	static List<List<Integer>> graph, reverseGraph;
	static boolean[] visited;
	static Stack<Integer> stack;
	static List<List<Integer>> sccList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] cost = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			String line = br.readLine();
			for(int j = 1; j <= N; j++) {
				char c = line.charAt(j-1);
				if(c == '1') {
					graph.get(i).add(j);
					reverseGraph.get(j).add(i);
				}
			}
		}
		
		visited = new boolean[N+1];
		stack = new Stack<>();
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				dfs(i);
			}
		}
		
		visited = new boolean[N+1];
		sccList = new ArrayList<>();
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			if(visited[curr]) continue;
			List<Integer> scc = new ArrayList<>();
			makeScc(curr, scc);	
			sccList.add(scc);
		}
		
		int result = 0;
		for(List<Integer> scc : sccList) {
			int min = 1_000_000_000;
			for(int v : scc) {
				min = Math.min(min, cost[v]);
			}
			result += min;
		}
		
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		visited[curr] = true;
		for(int next : graph.get(curr)) {
			if(!visited[next]) {
				dfs(next);
			}
		}
		stack.push(curr);
	}
	
	private static void makeScc(int curr, List<Integer> scc) {
		visited[curr] = true;
		scc.add(curr);
		for(int next : reverseGraph.get(curr)) {
			if(!visited[next]) {
				makeScc(next, scc);
			}
		}
	}
}