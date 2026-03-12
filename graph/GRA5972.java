package graph;

import java.io.*;
import java.util.*;

public class GRA5972 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int[] distance;
	static boolean[] visited;
	static int INF = 1_000_000_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph.get(A).add(new int[] {B, C});
			graph.get(B).add(new int[] {A, C});
		}
		
		dijkstra(1);
		System.out.println(distance[N]);
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<int[]> pqueue = new PriorityQueue<>(new Comparator<>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[1] - arr2[1];
			}
		});
		
		distance[start] = 0;
		pqueue.offer(new int[] {start, 0});
		
		while(!pqueue.isEmpty()) {
			int[] curr = pqueue.poll();
			
			if(visited[curr[0]]) continue;
			visited[curr[0]] = true;
			
			for(int[] next : graph.get(curr[0])) {
				if(distance[curr[0]] + next[1] < distance[next[0]]) {
					distance[next[0]] = distance[curr[0]] + next[1];
					pqueue.offer(new int[] {next[0], distance[next[0]]});
				}
			}
		}
	}
	
	private static void init(int N) {
		for(int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		distance = new int[N+1];
		for(int i = 1; i <= N; i++) {
			distance[i] = INF;
		}
		visited = new boolean[N+1];
	}
}