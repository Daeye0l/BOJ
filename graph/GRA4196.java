package graph;

import java.io.*;
import java.util.*;

public class GRA4196 {
	static List<List<Integer>> graph, reverseGraph, sccList;
	static boolean[] visited;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			init(N);
			
			for(int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				graph.get(x).add(y);
				reverseGraph.get(y).add(x);
			}
			
			// 정방향 그래프에 dfs를 수행하여 stack에 채워 넣기
			visited = new boolean[N+1];
			stack = new Stack<>();
			for(int j = 1; j <= N; j++) {
				if(!visited[j]) dfs(j);
			}
			
			// stack의 top부터 역방향 그래프에 dfs를 수행하여 scc 찾기
			visited = new boolean[N+1];
			while(!stack.isEmpty()) {
				int curr = stack.pop();
				if(visited[curr]) continue;
				List<Integer> scc = new ArrayList<>();
				findScc(curr, scc);
				sccList.add(scc);
			}
			
			int cnt = 0;
			int[] sccIndegree = getSccIndegree(N);
			for(int j = 0; j < sccList.size(); j++) {
				if(sccIndegree[j] == 0) cnt++;
			}
			result.append(cnt).append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();
		sccList = new ArrayList<>();
		for(int j = 0; j <= N; j++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}
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
	
	private static void findScc(int curr, List<Integer> scc) {
		visited[curr] = true;
		scc.add(curr);
		for(int next : reverseGraph.get(curr)) {
			if(!visited[next]) {
				findScc(next, scc);
			}
		}
	}
	
	private static int[] getSccIndegree(int N) {
		// 같은 SCC에 속한 각 도미노 블록에 id 부여
		int[] sccId = new int[N+1];
		for(int i = 0; i < sccList.size(); i++) {
			for(int num : sccList.get(i)) {
				sccId[num] = i;
			}
		}
		
		// SCC 자체를 하나의 노드로 보고 다른 SCC로 부터 들어오는 indegree가 있는지 구함
		int[] sccIndegree = new int[sccList.size()];
		for(int i = 1; i <= N; i++) {
			for(int next : graph.get(i)) {
				if(sccId[i] != sccId[next]) {
					sccIndegree[sccId[next]]++;
				}
			}
		}
		
		return sccIndegree;
	}
}