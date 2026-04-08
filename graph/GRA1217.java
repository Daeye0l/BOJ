package graph;

import java.io.*;
import java.util.*;

public class GRA1217 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder result = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0) break;
			
			init(M);
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a증세와 b증세가 같이 나타나면 환자가 죽음
				// = a와 b가 동시에 참이면 안됨
				// = !(a n b) = !a u !b
				// = (a -> !b) n (b -> !a)
				graph.get(changeNum(a)).add(changeNum(-b));
				graph.get(changeNum(b)).add(changeNum(-a));
			}
			
			for(int i = 1; i <= 2*M; i++) {
				if(id[i] == 0) dfs(i);
			}
			
			boolean isValid = true;
			for(int i = 1; i <= M; i++) {
				int pos = 2*M-1;
				int neg = 2*M;
				if(sccId[pos] == sccId[neg]) {
					isValid = false;
					break;
				}
			}
			
			if(isValid) result.append(1);
			else result.append(0);
			result.append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = ++idx;
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
	
	private static int changeNum(int num) {
		return num > 0 ? 2*num-1 : 2*(-num);
	}
	
	private static void init(int M) {
		graph = new ArrayList<>();
		idx = sccIdx = 0;
		id = new int[2*M+1];
		low = new int[2*M+1];
		sccId = new int[2*M+1];
		stack = new Stack<>();
		onStack = new boolean[2*M+1];
		
		for(int i = 0; i <= 2*M; i++) {
			graph.add(new ArrayList<>());
		}
	}
}