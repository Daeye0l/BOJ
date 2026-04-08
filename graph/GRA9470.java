package graph;

import java.io.*;
import java.util.*;

public class GRA9470 {
	static List<List<Integer>> graph;
	static int[] inDegree, strahler, maxPrevStrahler, maxPrevStrahlerCnt;
	static int maxStrahler;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder result = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			init(M);
			
			for(int p = 0; p < P; p++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				graph.get(A).add(B);
				inDegree[B]++;
			}
			
			topologicalSort(M);
			
			result.append(K).append(" ").append(maxStrahler).append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void topologicalSort(int M) {
		Queue<Integer> queue = new LinkedList<>();
		for(int m = 1; m <= M; m++) { 
			if(inDegree[m] == 0) queue.offer(m);
			strahler[m] = 1;
		}
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			maxStrahler = Math.max(maxStrahler, strahler[curr]);
			
			for(int next : graph.get(curr)) {
				inDegree[next]--;
				
				// 현재 노드의 스트랄러 값이 다음 노드의 이전 최대 스트랄러 값보다 큰경우
				if(strahler[curr] > maxPrevStrahler[next]) {
					maxPrevStrahler[next] = strahler[curr];
					maxPrevStrahlerCnt[next] = 1;
				}
				// 현재 노드의 스트랄러 값이 다음 노드의 이전 최대 스트랄러 값과 같은 경우
				else if(strahler[curr] == maxPrevStrahler[next]) {
					maxPrevStrahlerCnt[next]++;
				}
				
				if(inDegree[next] == 0) {
					if(maxPrevStrahlerCnt[next] >= 2) {
						strahler[next] = maxPrevStrahler[next]+1;
					}
					else strahler[next] = maxPrevStrahler[next];
					queue.offer(next);
				}
			}
		}
	}
	
	private static void init(int M) {
		graph = new ArrayList<>();
		inDegree = new int[M+1];
		strahler = new int[M+1];
		maxPrevStrahler = new int[M+1];
		maxPrevStrahlerCnt = new int[M+1];
		maxStrahler = 0;
		for(int m = 0; m <= M; m++) {
			graph.add(new ArrayList<>());
		}
	}
}