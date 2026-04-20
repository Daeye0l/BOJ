package graph;

import java.io.*;
import java.util.*;

public class GRA16367 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, sccId, low;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken()); // 램프의 개수
		int n = Integer.parseInt(st.nextToken()); // 참가자의 수
		
		init(k);
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Pair[] pairs = new Pair[3];
			
			for(int j = 0; j < 3; j++) {
				int l = Integer.parseInt(st.nextToken());
				char c = st.nextToken().charAt(0);
				pairs[j] = new Pair(l, c);
			}
			
			// 3개의 예측 결과 중 2개 이상이 참이어야 하므로 2개 이상이 거짓이 될 수 없음 
			// (a v b) ∧ (b v c) ∧ (a v c) -> 어느 2개를 골랐을 때 2개가 동시에 거짓이 될 수 없음
			// (a v b) = ¬a -> b, ¬b -> a 로 표현할 수 있음
			int a = pairs[0].c == 'R' ? pairs[0].l : -pairs[0].l;
			int b = pairs[1].c == 'R' ? pairs[1].l : -pairs[1].l;
			int c = pairs[2].c == 'R' ? pairs[2].l : -pairs[2].l;
			
			graph.get(changeNum(-a)).add(changeNum(b));
			graph.get(changeNum(-b)).add(changeNum(a));
			graph.get(changeNum(-b)).add(changeNum(c));
			graph.get(changeNum(-c)).add(changeNum(b));
			graph.get(changeNum(-a)).add(changeNum(c));
			graph.get(changeNum(-c)).add(changeNum(a));
		}
		
		for(int i = 1; i <= 2*k; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		StringBuilder result = new StringBuilder();
		
		boolean isValid = true;
		for(int i = 1; i <= k; i++) {
			int pos = 2*i-1;
			int neg = 2*i;
			if(sccId[pos] == sccId[neg]) {
				isValid = false;
				break;
			}
		}
		
		if(!isValid) {
			result.append(-1);
			System.out.println(result);
			return;
		}
		else {
			for(int i = 1; i <= k; i++) {
				int pos = 2*i-1;
				int neg = 2*i;
				if(sccId[pos] < sccId[neg]) {
					result.append('R');
				}
				else result.append('B');
			}
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
		return num > 0 ? 2 * num - 1 : 2 * (-num);
	}
	
	private static void init(int k) {
		graph = new ArrayList<>();
		idx = sccIdx = 0;
		id = new int[2*k+1];
		sccId = new int[2*k+1];
		low = new int[2*k+1];
		onStack = new boolean[2*k+1];
		stack = new Stack<>();
		
		for(int i = 0; i <= 2*k; i++) {
			graph.add(new ArrayList<>());
		}
	}
	
	private static class Pair {
		private int l;
		private char c;
		
		Pair(int l, char c) {
			this.l = l;
			this.c = c;
		}
	}
}