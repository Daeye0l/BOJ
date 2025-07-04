package dp;

import java.io.*;
import java.util.*;

public class DP1516 {
	static int n;
	static List<List<Integer>> graph;
	static int[] inDegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		inDegree = new int[n+1];
		int[] time = new int[n+1]; // 각 건물을 짓는데 걸리는 시간
		int[] dp = new int[n+1]; // 각 건물이 완성되기까지 걸리는 최소 시간
		
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int num = 1; num <= n; num++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 각 건물의 건설 시간을 저장
			int t = Integer.parseInt(st.nextToken());
			time[num] = t;
			dp[num] = t;
			
			// 그래프 관계를 구성
			while(true) {
				int prev = Integer.parseInt(st.nextToken());
				if(prev == -1) {
					break;
				}
				graph.get(prev).add(num);
				inDegree[num] += 1;
			}
		}
		
		// 위상정렬 결과로 dp값 업데이트
		List<Integer> result = topologicalSort();
		for(int curr : result) {
			for(int next : graph.get(curr)) {
				dp[next] = Math.max(dp[next], dp[curr] + time[next]);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
		    sb.append(dp[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static List<Integer> topologicalSort() {
		Queue<Integer> que = new LinkedList<>();
		List<Integer> result = new ArrayList<>();
		
		// 진입차수가 0인 건물 번호 큐에 삽입
		for(int num = 1; num <= n; num++) {
			if(inDegree[num] == 0) {
				que.offer(num);
			}
		}
		
		while(!que.isEmpty()) {
			int curr = que.poll();
			result.add(curr);
			
			// 현재 건물에 연결된 건물들의 차수를 감소하고 진입 차수가 없다면 큐에 삽입
			for(int next : graph.get(curr)) {
				inDegree[next] -= 1;
				if(inDegree[next] == 0) {
					que.offer(next);
				}
			}
		}
		
		return result;
	}
}