package graph;

import java.io.*;
import java.util.*;

public class GRA3977 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;

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
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				graph.get(A).add(B);
			}
			
			// SCC 구성하기
			for(int j = 0; j < N; j++) {
				if(id[j] == -1) dfs(j);
			}
			
			// SCC 단위의 진입차수 구하기
			int sccCnt = sccIdx;
			int[] sccIndegree = new int[sccCnt];
			for(int j = 0; j < N; j++) {
				for(int next : graph.get(j)) {
					if(sccId[j] != sccId[next]) {
						sccIndegree[sccId[next]]++;
					}
				}
			}
			
			// 진입차수가 0인 SCC가 한개만 있을 때 해당 SCC 안에 있는 구역은 시작점이 될 수 있음
			int startScc = 0;
			int startCnt = 0;
			for(int j = 0; j < sccCnt; j++) {
				if(sccIndegree[j] == 0) {
					startScc = j;
					startCnt++;
				}
			}
			
			if(startCnt == 1) {
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < N; j++) {
					if(sccId[j] == startScc) {
						sb.append(j).append('\n');
					}
				}
				sb.deleteCharAt(sb.length()-1);
				result.append(sb);
			}
			else result.append("Confused");
			if(i < T-1) result.append("\n\n");
			
			if(i < T-1) br.readLine();
		}
		
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = idx++;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			if(id[next] == -1) {
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
	
	private static void init(int N) {
		graph = new ArrayList<>();
		idx = sccIdx = 0;
		id = new int[N];
		low = new int[N];
		sccId = new int[N];
		stack = new Stack<>();
		onStack = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}
		Arrays.fill(id, -1);
		Arrays.fill(sccId, -1);
	}
}