package graph;

import java.io.*;
import java.util.*;

public class GRA16915 {
	static List<List<Integer>> graph;
	static List<List<Integer>> connectedSwitch = new ArrayList<>();
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N, M);
		
		boolean[] states = new boolean[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			if(Integer.parseInt(st.nextToken()) == 1) states[i] = true;	
			else states[i] = false;			
		}
		
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			for(int j = 0; j < K; j++) {
				int k = Integer.parseInt(st.nextToken());
				connectedSwitch.get(k).add(i);
			}
		}
		
		for(int i = 1; i <= N; i++) {
			int a = connectedSwitch.get(i).get(0);
			int b = connectedSwitch.get(i).get(1);
			
			// 현재 방이 열려있는 경우(2개의 스위치 모두 누르거나 아예 누르지 말아야 함) -> XNOR
			// 2-SAT식의 조건을 만족하기 위해 XNOR을 변경 -> (A v !B) n (!A v B)
			if(states[i]) {
				graph.get(changeNum(-a)).add(changeNum(-b));
				graph.get(changeNum(b)).add(changeNum(a));
				graph.get(changeNum(a)).add(changeNum(b));
				graph.get(changeNum(-b)).add(changeNum(-a));
			}
			// 현재 방이 닫혀있는 경우(2개의 스위치 중 하나만 눌러야함) -> XOR
			// 2-SAT식의 조건을 만족하기 위해 XOR을 변경 -> (A v B) n (!A v !B)
			else {
				graph.get(changeNum(-a)).add(changeNum(b));
				graph.get(changeNum(-b)).add(changeNum(a));
				graph.get(changeNum(a)).add(changeNum(-b));
				graph.get(changeNum(b)).add(changeNum(-a));
			}
		}
		
		for(int i = 1; i <= 2*M; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		boolean isValid = true;
		for(int i = 1; i <= M; i++) {
			int pos = 2*i-1;
			int neg = 2*i;
			
			if(sccId[pos] == sccId[neg]) {
				isValid = false;
				break;
			}
		}
		
		if(isValid) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = ++idx;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next: graph.get(curr)) {
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
	
	private static void init(int N, int M) {
		graph = new ArrayList<>();
		connectedSwitch = new ArrayList<>();
		idx = sccIdx = 0;
		id = new int[2*M+1];
		low = new int[2*M+1];
		sccId = new int[2*M+1];
		stack = new Stack<>();
		onStack = new boolean[2*M+1];
		
		for(int i = 0; i <= 2*M; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i <= N; i++) {
			connectedSwitch.add(new ArrayList<>());
		}
	}
}