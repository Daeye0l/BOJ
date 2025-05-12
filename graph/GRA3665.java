package graph;

import java.io.*;
import java.util.*;

public class GRA3665 {
	static List<List<Integer>> graph;
	static int[] inDegree;
	static StringBuilder result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		while(t --> 0) {
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 팀들의 작년 순위를 입력 받고 
			int[] ranking = new int[n];
			for(int i = 0; i < n; i++) {
				ranking[i] = Integer.parseInt(st.nextToken());
			}
			
			// 연결 그래프 생성, 진입 차수 증가
			graph = new ArrayList<>();
			for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
			inDegree = new int[n+1];
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					graph.get(ranking[i]).add(ranking[j]);
					inDegree[ranking[j]]++;
				}
			}
			
			// 등수가 바뀐 쌍에 대하여 연결 관계와 진입 차수 수정
			int m = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// 작년 순위에서 b의 순위가 a보다 높았던 경우
				if(graph.get(b).contains(a)) {
					graph.get(b).remove(Integer.valueOf(a));
					graph.get(a).add(b);
					inDegree[a]--;
					inDegree[b]++;
				}
				// 작년 순위에서 a의 순위가 b보다 높았던 경우
				else if(graph.get(a).contains(b)) {
					graph.get(a).remove(Integer.valueOf(b));
					graph.get(b).add(a);
					inDegree[b]--;
					inDegree[a]++;
				}
			}
			
			System.out.println(topologicalSort(n));
		}
	}
	
	private static String topologicalSort(int n) {
		Queue<Integer> que = new LinkedList<>();
		result = new StringBuilder();
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(inDegree[i] == 0) {
				que.offer(i);
			}
		}
		
		while(!que.isEmpty()) {
			// 위상정렬 진행 중에 진입 차수가 0인 팀이 2개 이상인 경우는 확실한 순위를 찾을 수 없음
			if(que.size() > 1) return "?";
			int curr = que.poll();
			result.append(curr).append(" ");
			cnt++;
			for(int next : graph.get(curr)) {
				inDegree[next]--;
				if(inDegree[next] == 0) {
					que.offer(next);
				}
			}
		}
		
		// 위상정렬을 진행한 결과가 모든 팀을 포함하지 못해 순위를 정할 수 없는 경우
		if(cnt != n) return "IMPOSSIBLE";
		else return result.toString();
	}
}