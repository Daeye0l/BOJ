package graph;

import java.io.*;
import java.util.*;

public class GRA2623 {
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		indegree = new int[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			int[] sequence = new int[cnt];
			
			for(int j = 0; j < cnt; j++) {
				sequence[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j < cnt-1; j++) {
				graph.get(sequence[j]).add(sequence[j+1]);
				indegree[sequence[j+1]]++;
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(indegree[i] == 0) queue.offer(i);
		}
		
		List<Integer> result = topologicalSort(queue);
		if(result.size() != N) {
			System.out.println(0);
		}
		else {
			for(int r : result) {
				System.out.println(r);
			}	
		}
	}
	
	private static List<Integer> topologicalSort(Queue<Integer> queue) {
		List<Integer> result = new ArrayList<>();
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			result.add(curr);
			
			for(int next : graph.get(curr)) {
				indegree[next]--;
				if(indegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		return result;
	}
}