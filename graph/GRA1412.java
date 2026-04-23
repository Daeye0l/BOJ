package graph;

import java.io.*;
import java.util.*;

public class GRA1412 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] matrix = new boolean[N][N];
		init(N);
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				if(line.charAt(j) == 'Y') {
					matrix[i][j] = true;
				}
			}
		}
		
		// 양방향 간선이 없는 정점끼리 연결해서 그래프를 구성한 후 DAG인지 확인하는 것이 핵심
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(matrix[i][j] && !matrix[j][i]) {
					graph.get(i).add(j);
				}
			}
		}
		
		// 타잔 알고리즘으로 SCC 구하기
		for(int i = 0; i < N; i++) {
			if(id[i] == 0) {
				dfs(i);
			}
		}
		
		// 특정 SCC에서 사이클이 존재하면 그건 불가능한 경우
		boolean isValid = true;
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 0; j < N; j++) {
				if(sccId[j] == i) cnt++;
			}
			if(cnt >= 2) {
				isValid = false;
				break;
			}
		}
		
		if(isValid) System.out.println("YES");
		else System.out.println("NO");
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
	
	private static void init(int N) {
		graph = new ArrayList<>();
		idx = 0;
		sccIdx = 0;
		id = new int[N];
		low = new int[N];
		sccId = new int[N];
		onStack = new boolean[N];
		stack = new Stack<>();
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
	}
}