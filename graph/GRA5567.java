package graph;

import java.io.*;
import java.util.*;

public class GRA5567 {
	static List<List<Integer>> connections = new ArrayList<>();
	static boolean[] visited;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i <= n; i++) {
			connections.add(new ArrayList<>());
		}
		visited = new boolean[n+1];
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			connections.get(a).add(b);
			connections.get(b).add(a);
		}
		
		bfs(1);
		
		System.out.println(result);
	}
	
	private static void bfs(int start) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(start, 0));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Node curr = queue.poll();
			
			if(curr.distance != 0 && curr.distance <= 2) {
				result++;
			}
			if(curr.distance > 2) {
				continue;
			}
			
			for(int next : connections.get(curr.num)) {
				if(!visited[next]) {
					queue.offer(new Node(next, curr.distance+1));
					visited[next] = true;
				}				
			}
		}
	}
	
	private static class Node {
		private int num;
		private int distance;
		
		Node(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
	}
}