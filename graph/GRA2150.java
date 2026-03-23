package graph;

import java.io.*;
import java.util.*;

public class GRA2150 {
	static List<List<Integer>> graph;
	static int idx = 0;
	static int[] id, back;
	static boolean[] onStack;
	static Stack<Integer> stack;
	static List<List<Integer>> sccList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		init(V);
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}
		
		for(int i = 1; i <= V; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		StringBuilder result = new StringBuilder();
		result.append(sccList.size()).append('\n');
		sccList.sort(new Comparator<>() {
			public int compare(List<Integer> l1, List<Integer> l2) {
				return l1.get(0)-l2.get(0);
			}
		});
		for(List<Integer> scc : sccList) {
			for(int v : scc) {
				result.append(v).append(' ');
			}
			result.append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		idx++;
		id[curr] = idx; // DFS 방문 순서(id) 저장
		back[curr] = idx; // 역방향 엣지를 통해 도달 가능한 최소 id 저장
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			if(id[next] == 0) {
				dfs(next);
				back[curr] = Math.min(back[curr], back[next]);
			}
			else if(onStack[next]){
				back[curr] = Math.min(back[curr], id[next]);
			}
		}
		
		if(id[curr] == back[curr]) {
			List<Integer> scc = new ArrayList<>();
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				scc.add(v);
				if(v == curr) break;
			}
			Collections.sort(scc);
			scc.add(-1);
			sccList.add(scc);
		}
	}
	
	private static void init(int V) {
		graph = new ArrayList<>();
		for(int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}
		id = new int[V+1]; // DFS 방문 순서
		back = new int[V+1]; // back은 정점 v에서 역방향 엣지를 통해 도달 가능한 최소 id
		onStack = new boolean[V+1]; // 정점 v가 스택에 있는지 여부
		stack = new Stack<>();
		sccList = new ArrayList<>();
	}
}