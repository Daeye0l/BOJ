package graph;

import java.io.*;
import java.util.*;

public class GRA4013 {
	static int N, M;
	static List<List<Integer>> graph, reverseGraph;
	static int[] ATM, sccId, sccIndegree, sccMoney, dp;
	static Set<Integer> restaurant, restaurantScc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}
		ATM = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph.get(start).add(end);
			reverseGraph.get(end).add(start);
		}
		
		for(int i = 1; i <= N; i++) {
			ATM[i] = Integer.parseInt(br.readLine());
		}
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		restaurant = new HashSet<>();
		restaurantScc = new HashSet<>();
		for(int i = 0; i < P; i++) {
			restaurant.add(Integer.parseInt(st.nextToken()));
		}
		
		// SCC 구성하기
		int sccCnt = makeScc();
		// 구성된 SCC로 DAG 만들기
		List<List<Integer>> sccGraph = makeDag(sccCnt);
		
		// 시작점이 속한 SCC에서 시작해서 BFS로 정답 구하기
		Queue<Integer> queue = new LinkedList<>();
		int[] dp = new int[sccCnt];
		for(int i = 0; i < sccCnt; i++) {
			dp[i] = -1;
		}
		
		int startScc = sccId[S];
		dp[startScc] = sccMoney[startScc];
		queue.offer(startScc);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int next : sccGraph.get(curr)) {
				if(dp[next] < dp[curr] + sccMoney[next]) {
					dp[next] = dp[curr] + sccMoney[next];
					queue.offer(next);
				}
			}
		}
		
		int max = 0;
		for(int i = 0; i < sccCnt; i++) {
			// 레스토랑을 포함하는 SCC에서만 최댓값 찾기
			if(restaurantScc.contains(i)) {
				max = Math.max(max, dp[i]);
			}
		}
		
		System.out.println(max);
	}
	
	private static List<List<Integer>> makeDag(int sccCnt) {
		List<List<Integer>> sccGraph = new ArrayList<>();
		for(int i = 0; i < sccCnt; i++) {
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
	
	private static int makeScc() {
		// 정방향 dfs로 stack에 push
		Stack<Integer> stack = new Stack<>();
		boolean[] visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				dfs(i, visited, stack);
			}
		}
		
		// 역방향 dfs로 각 교차로를 SCC로 그룹화
		sccId = new int[N+1];
		int idx = 0;
		visited = new boolean[N+1];
		while(!stack.isEmpty()) {
			int curr = stack.pop();
			if(!visited[curr]) {
				findSccId(curr, visited, idx);
				idx++;
			}
		}
		
		// 각 SCC에서 인출할 수 있는 현금 구하기
		int sccCnt = idx;
		sccMoney = new int[sccCnt];
		for(int i = 1; i <= N; i++) {
			sccMoney[sccId[i]] += ATM[i];
		}
		
		return sccCnt;
	}
	
	private static void dfs(int curr, boolean[] visited, Stack<Integer> stack) {
		visited[curr] = true;
		for(int next : graph.get(curr)) {
			if(!visited[next]) {
				dfs(next, visited, stack);
			}
		}
		stack.push(curr);
	}
	
	private static void findSccId(int curr, boolean[] visited, int idx) {
		visited[curr] = true;
		sccId[curr] = idx;
		if(restaurant.contains(curr)) {
			restaurantScc.add(idx);
		}
		
		for(int next : reverseGraph.get(curr)) {
			if(!visited[next]) {
				findSccId(next, visited, idx);
			}
		}
	}
}