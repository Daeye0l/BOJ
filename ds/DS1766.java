package ds;

import java.io.*;
import java.util.*;

public class DS1766 {
	static List<List<Integer>> graph;
	static int[] inDegree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 각 문제의 연결 관계와 진입 차수를 저장
		graph = new ArrayList<>();
		inDegree = new int[n+1];
		for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b); // a 문제를 풀어야 b 문제를 풀 수 있음(단방향 연결)
			inDegree[b]++; // b의 진입 차수 증가
		}
		
		System.out.println(topologicalSort(n));
	}
	
	private static StringBuilder topologicalSort(int n) {
		StringBuilder result = new StringBuilder();
		// 쉬운 문제 오름차순으로 큐를 정렬
		PriorityQueue<Integer> que = new PriorityQueue<>(new Comparator<>() {
			public int compare(Integer n1, Integer n2) {
				return n1 - n2;
			}
		});
		
		// 진입 차수가 0인 문제를 큐에 삽입
		for(int i = 1; i <= n; i++) {
			if(inDegree[i] == 0) {
				que.offer(i);
			}
		}
		
		while(!que.isEmpty()) {
			// 큐에서 문제를 하나 빼고 결과에 삽입
			int curr = que.poll();
			result.append(curr).append(" ");
			// 다음 문제의 진입 차수를 감소하고 차수가 0이 되면 큐에 삽입
			for(int next : graph.get(curr)) {
				inDegree[next]--;
				if(inDegree[next] == 0) {
					que.offer(next);
				}
			}
		}
		
		return result;
	}
}