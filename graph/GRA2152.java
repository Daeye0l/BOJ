package graph;

import java.io.*;
import java.util.*;

public class GRA2152 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		// 그래프 구성 
		graph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
		}
		
		// 타잔 알고리즘으로 각 도시의 SCC id 구하기
		idx = 1;
		sccIdx = 0;
		id = new int[N+1];
		low = new int[N+1];
		sccId = new int[N+1];
		stack = new Stack<>();
		onStack = new boolean[N+1];
		map = new HashMap<>(); // 각 SCC에 포함된 도시의 개수 저장
		for(int i = 1; i <= N; i++) {
			if(id[i] == 0) {
				dfs(i);
			}
		}
		
		// 나눠진 SCC로 DAG 만들기
		int sccCnt = sccIdx;
		List<List<Integer>> sccGraph = makeDag(N, sccCnt);
		
		// 각 SCC의 진입차수 구하기
		int[] sccIndegree = new int[sccCnt+1];
		for(int i = 1; i <= sccCnt; i++) {
			for(int next : sccGraph.get(i)) {
				sccIndegree[next]++;
			}
		}
		
		int startScc = sccId[S];
		int endScc = sccId[T];
		int[] dp = new int[sccCnt+1];
		dp[startScc] = map.get(startScc);
		
		// 위상정렬을 위해 진입차수가 0인 SCC먼저 큐에 넣기
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= sccCnt; i++) {
			if(sccIndegree[i] == 0) queue.offer(i);
		}
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int next : sccGraph.get(curr)) {
				// startScc부터 시작해서 현재 SCC까지 도달 가능한 경우에만 갱신 
				if(dp[curr] != 0) {
					dp[next] = Math.max(dp[next], dp[curr] + map.get(next));
				}
				
				sccIndegree[next]--;
				if(sccIndegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		System.out.println(dp[endScc]);
	}
	
	private static List<List<Integer>> makeDag(int N, int sccCnt) {
		List<List<Integer>> sccGraph = new ArrayList<>();
		for(int i = 0; i <= sccCnt; i++) {
			sccGraph.add(new ArrayList<>());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int next : graph.get(i)) {
				if(sccId[i] != sccId[next]) {
					sccGraph.get(sccId[i]).add(sccId[next]);
				}
			}
		}
		
		return sccGraph;
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
			int cnt = 0;
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				cnt++;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
			map.put(sccIdx, cnt);
		}
	}
}