package graph;

import java.io.*;
import java.util.*;

public class GRA11724 {
	static Node[] nodes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 정점 개수와 간선 개수 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); 
		
		// 각 정점들을 생성하여 배열에 저장
		nodes = new Node[N+1];
		for(int i = 1; i <= N; i++) {
			nodes[i] = new Node(i);
		}
		
		// 두 정점을 서로 양방향 연결
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Node n1 = nodes[Integer.parseInt(st.nextToken())];
			Node n2 = nodes[Integer.parseInt(st.nextToken())];
			if(!n1.adjacent.contains(n2)) n1.adjacent.add(n2);
			if(!n2.adjacent.contains(n1)) n2.adjacent.add(n1);
		}
		
		// 연결 요소의 개수를 구해서 저장
		int cnt = 0;
		for(int i = 1; i <= N; i++) {
			if(!nodes[i].visited) {
				dfs(nodes[i]);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	// 깊이우선탐색 메소드
	private static void dfs(Node root) {
		root.visited = true;
		for(Node n : root.adjacent) {
			if(!n.visited) {
				dfs(n);
			}
		}
	}
	
	// 정점객체를 생성할 클래스
	static class Node {
		int value;
		boolean visited;
		ArrayList<Node> adjacent = new ArrayList<>();
		
		public Node(int value) {
			this.value = value;
			this.visited = false;
		}
	}
}