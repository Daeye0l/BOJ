package graph;

import java.io.*;
import java.util.*;

public class GRA2207 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(M);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			graph.get(changeNum(-x)).add(changeNum(y));
			graph.get(changeNum(-y)).add(changeNum(x));
		}
		
		for(int i = 1; i <= 2*M; i++) {
			if(id[i] == 0) {
				dfs(i);
			}
		}
		
		for(int i = 1; i <= M; i++) {
			int pos = 2*i-1;
			int neg = 2*i;
			if(sccId[pos] == sccId[neg]) {
				System.out.println("OTL");
				return;
			}
		}
		System.out.println("^_^");
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
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
			sccIdx++;
		}
	}
	
	private static int changeNum(int num) {
		return num > 0 ? 2*num-1 : 2*(-num);
	}
	
	private static void init(int M) {
		graph = new ArrayList<>();
		idx = sccIdx = 1;
		id = new int[2*M+1];
		low = new int[2*M+1];
		sccId = new int[2*M+1];
		onStack = new boolean[2*M+1];
		stack = new Stack<>();
		
		for(int i = 0; i <= 2*M; i++) {
			graph.add(new ArrayList<>());
		}
	}
}