package graph;

import java.io.*;
import java.util.*;

public class GRA26146 {
	static List<List<Integer>> graph;
	static int[] id, low, sccId;
	static int idx, sccIdx;
	static Stack<Integer> stack;
	static boolean[] onStack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph.get(v).add(w);
		}
		
		for(int i = 1; i <= N; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		int sccCnt = sccIdx;
		if(sccCnt == 1) {
			System.out.println("Yes");
		}
		else System.out.println("No");
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = idx++;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			if(id[next] == 0) {
				dfs(next);
				low[curr] = Math.min(low[curr], low[next]);
			}
			else if(onStack[next]) {
				low[curr] = Math.min(low[curr], id[next]);
			}
		}
		
		if(id[curr] == low[curr]) {
			sccIdx++;
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
		}
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		id = new int[N+1];
		low = new int[N+1];
		sccId = new int[N+1];
		idx = 1;
		sccIdx = 0;
		stack = new Stack<>();
		onStack = new boolean[N+1];
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
	}
}