package graph;

import java.io.*;
import java.util.*;

public class GRA1432 {
	static List<List<Integer>> graph;
	static int[] indegree;
	static int[] result;
	static int visitedCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		init(N);
		
		// 모든 간선 V1 -> V2에 대해 V2의 번호가 V1보다 항상 크려면
		// 위상정렬상 나중에 오는 순서가 큰 수여야 가능함
		// 모든 간선을 뒤집어서 위상정렬 후 N~1 까지 부여하면 됨
		for(int i = 1; i <= N; i++) {
			String line = br.readLine();
			for(int j = 1; j <= N; j++) {
				if(line.charAt(j-1) == '1') {
					// 간선 뒤집어서 저장
					graph.get(j).add(i);
					indegree[i]++;
				}
			}
		}
		
		topologicalSort(N);
		if(visitedCnt < N) System.out.println(-1);
		else {
			for(int i = 1; i <= N; i++) {
				System.out.print(result[i] + " ");
			}
		}
	}
	
	private static void topologicalSort(int N) {
		// 사전순이 되기 위해서는 진입 차수가 0인 정점이 여러개 있을 때 큰 수부터 번호를 부여해야 함
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		int newNum = N;
		while(!pq.isEmpty()) {
			int curr = pq.poll();
			visitedCnt++;
			result[curr] = newNum--;
			
			for(int next : graph.get(curr)) {
				indegree[next]--;
				if(indegree[next] == 0) {
					pq.offer(next);
				}
			}
		}
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		indegree = new int[N+1];
		result = new int[N+1];
		visitedCnt = 0;
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
	}
}