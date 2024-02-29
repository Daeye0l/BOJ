package graph;

import java.io.*;
import java.util.*;

public class GRA11725 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 노드의 개수 입력받기
		
		// 1~n의 번호를 가진 노드들을 생성해서 배열에 저장
		Node[] nodes = new Node[n+1];
		for(int i = 1; i <= n; i++) {
			nodes[i] = new Node(i);
		}
		
		// n-1개의 두 정점 사이의 연결관계를 구현
		for(int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Node a = nodes[Integer.parseInt(st.nextToken())];
			Node b = nodes[Integer.parseInt(st.nextToken())];
			a.adjacent.add(b);
			b.adjacent.add(a);
		}
		
		dfs(nodes[1]); // 루트 노드인 정점 1을 시작으로 탐색
		
		for(int i = 2; i <= n; i++) {
			System.out.println(nodes[i].parent.num);
		}
	}
	
	// 깊이 우선 탐색을 하면서 각 정점의 부모 노드를 저장
	private static void dfs(Node root) {
		root.visited = true;
		for(Node adj : root.adjacent) {
			if(!adj.visited) {
				adj.parent = root;
				dfs(adj);
			}
		}
	}
	
	private static class Node {
		private int num;
		private boolean visited;
		private Node parent; // 해당 정점의 부모 정점을 저장
		private List<Node> adjacent; // 해당 정점과 인접한 정점들을 저장
 		public Node(int num) {
			this.num = num;
			this.visited = false;
			this.parent = null;
			this.adjacent = new ArrayList<>();
		}
	}
}