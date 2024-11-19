package graph;

import java.io.*;
import java.util.*;

public class GRA15591 {
	static int N;
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 영상의 갯수
		int Q = Integer.parseInt(st.nextToken()); // 질문의 갯수
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		// p영상과 q영상을 r의 유사도를 가지고 연결
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			graph.get(p).add(new Node(q, r));
			graph.get(q).add(new Node(p, r));
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(bfs(k, v)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int bfs(int k, int v) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		int cnt = 0;
		
		visited[v] = true;
		que.offer(v);
		while(!que.isEmpty()) {
			int curr = que.poll();
			for(Node next : graph.get(curr)) {
				if(!visited[next.num]) {
					// 영상 a와 b가 있을 때 경로 상에 있는 유사도가 하나라도 k보다 작다면
					// a영상에서 b영상은 절대 추천될 수 없음
					if(next.usado >= k) {
						visited[next.num] = true;
						que.offer(next.num);
						cnt++;
					}	
				}
			}
		}
		
		return cnt;
	}
	
	private static class Node {
		private int num;
		private int usado;
		
		Node(int num, int usado) {
			this.num = num;
			this.usado = usado;
			
		}
	}
}