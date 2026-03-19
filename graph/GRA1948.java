package graph;

import java.io.*;
import java.util.*;

public class GRA1948 {
	static List<List<Node>> graph, reverseGraph;
	static int[] indegree, cumulTime;
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		init(n);
		
		StringTokenizer st;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(new Node(v, w));
			reverseGraph.get(v).add(new Node(u, w));
			indegree[v]++;
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		
		topologicalSort(queue, end);
		cnt = 0;
		dfs(end);
		
		System.out.println(cumulTime[end]);
		System.out.println(cnt);
	}
	
	private static void topologicalSort(Queue<Integer> queue, int end) {
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			if(curr == end) return;
			
			for(Node next : graph.get(curr)) {
				cumulTime[next.num] = Math.max(cumulTime[next.num], cumulTime[curr] + next.time);
				indegree[next.num]--;
				if(indegree[next.num] == 0) {
					queue.offer(next.num);
				}
			}
		}	
	}
	
	private static void dfs(int curr) {
		visited[curr] = true;
		
		for(Node next : reverseGraph.get(curr)) {
			if(cumulTime[next.num] + next.time == cumulTime[curr]) {
				// 최대 시간을 가지는 경로가 꼭 하나라는 법은 없기 때문에 도로 조건 먼저 체크
				cnt++;
				if(!visited[next.num]) {
					dfs(next.num);	
				}
			}
		}
	}
	
	private static void init(int n) {
		graph = new ArrayList<>();
		reverseGraph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			reverseGraph.add(new ArrayList<>());
		}
		indegree = new int[n+1];
		cumulTime = new int[n+1];
		visited = new boolean[n+1];
	}
	
	private static class Node {
		private int num;
		private int time;
		
		Node(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
}