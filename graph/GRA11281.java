package graph;

import java.io.*;
import java.util.*;

public class GRA11281 {
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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(changeNum(-a)).add(changeNum(b));
			graph.get(changeNum(-b)).add(changeNum(a));
		}
		
		for(int i = 1; i <= 2*N; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		for(int i = 1; i <= N; i++) {
			int pos = 2*i-1;
			int neg = 2*i;
			if(sccId[pos] == sccId[neg]) {
				System.out.println(0);
				return;
			}
		}
		
		StringBuilder result = new StringBuilder();
		result.append(1).append('\n');
		for(int i = 1; i <= N; i++) {
			int pos = 2*i-1;
			int neg = 2*i;
			// X -> ㄱX인 경우
			if(sccId[pos] > sccId[neg]) result.append(0).append(" ");
		    // ㄱX -> X인 경우
			else result.append(1).append(" ");
		}
		System.out.println(result);
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
	
	private static void init(int N) {
		graph = new ArrayList<>();
		for(int i = 0; i <= 2*N; i++) {
			graph.add(new ArrayList<>());
		}
		id = new int[2*N+1];
		low = new int[2*N+1];
		sccId = new int[2*N+1];
		idx = sccIdx = 1;
		stack = new Stack<>();
		onStack = new boolean[2*N+1];
	}
}