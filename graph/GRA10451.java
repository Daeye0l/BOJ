package graph;

import java.io.*;
import java.util.*;

public class GRA10451 {
	static List<List<Integer>> connections;
	static boolean[] visited;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0 ; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			init(N);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				connections.get(j).add(Integer.parseInt(st.nextToken()));	
			}
			
			int cnt = 0;
			for(int j = 1; j <= N; j++) {
				if(!visited[j]) {
					bfs(j);
					cnt++;
				}
			}
			
			result.append(cnt).append("\n");
		}
		
		System.out.println(result);
	}
	
	private static void init(int N) {
		connections = new ArrayList<>();
		for(int j = 0; j <= N; j++) {
			connections.add(new ArrayList<>());
		}
		visited = new boolean[N+1];
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			for(int next : connections.get(curr)) {
				if(!visited[next]) {
					queue.offer(next);
					visited[next] = true;
				}
			}
		}
	}
}