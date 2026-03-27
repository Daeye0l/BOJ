package graph;

import java.io.*;
import java.util.*;

public class GRA6543 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder result = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n == 0 && !st.hasMoreElements()) break;
			int m = Integer.parseInt(st.nextToken());
			
			init(n);
			
			// 노드간의 그래프 구성
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				graph.get(v).add(w);
			}
			
			// 각 노드에 SCC id를 부여해 SCC로 분리
			for(int i = 1; i <= n; i++) {
				if(id[i] == 0) {
					dfs(i);
				}
			}
			
			// SCC를 하나의 노드로 생각하고 진출차수 구하기
			int sccCnt = sccIdx;
			int[] outDegree = new int[sccCnt+1];
			for(int i = 1; i <= n; i++) {
				for(int next : graph.get(i)) {
					if(sccId[i] != sccId[next]) {
						outDegree[sccId[i]]++;
					}
				}
			}
			
			// SCC를 하나의 노드로 보면 그래프는 DAG이기 때문에 진출차수가 있으면 도달 가능하지만 다시 돌아올 수 없음
			boolean[] validScc = new boolean[sccCnt+1];
			for(int i = 1; i <= sccCnt; i++) {
				if(outDegree[i] == 0) {
					validScc[i] = true;
				}
			}
			
			// bottom(G) 구하기
			List<Integer> bottomG = new ArrayList<>();
			for(int i = 1; i <= n; i++) {
				if(validScc[sccId[i]]) {
					bottomG.add(i);
				}
			}
			Collections.sort(bottomG);
			
			if(bottomG.isEmpty()) {
				result.append(' ');
			}
			else {
				for(int v : bottomG) {
					result.append(v).append(' ');	
				}
			}
			result.append('\n');
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
			sccIdx++;
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
		}
	}
	
	private static void init(int n) {
		graph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		idx = 1; sccIdx = 0;
		id = new int[n+1];
		low = new int[n+1];
		sccId = new int[n+1];
		onStack = new boolean[n+1];
		stack = new Stack<>();
	}
}