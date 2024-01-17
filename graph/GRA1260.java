package graph;

import java.io.*;
import java.util.*;

public class GRA1260 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		int V = Integer.parseInt(st.nextToken()); // 시작할 정점의 번호

		Graph graph = new Graph(N);

		for(int i = 0; i < M; i++) { // 정점을 입력 받아 그래프에 추가
			st = new StringTokenizer(br.readLine());
			graph.add(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		for(int i = 1; i < N + 1; i++) {
			Collections.sort(graph.nodes[i].adjacent, new Comparator<Graph.Node>() {
				public int compare(Graph.Node n1, Graph.Node n2) {
					return n1.data -n2.data;
				}
			});
		}
		
		graph.DFS(graph.nodes[V]); // 시작 정점번호에 대하여 DFS 실행
		for(int i = 1; i < N + 1; i++) { // 정점 방문여부 초기
			graph.nodes[i].visited = false;
		}
		System.out.println();
		graph.BFS(graph.nodes[V]); // 시작 정점번호에 대하여 BFS 실행
		
	}
}

class Graph {
	class Node {
		int data; // 정점 번호를 저장
		LinkedList<Node> adjacent; // 인접한 정점을 저장
		boolean visited; // 정점 방문 여부

		public Node(int value) { // 생성자
			this.data = value;
			this.adjacent = new LinkedList<Node>();
			this.visited = false;
		}
	}

	Node[] nodes;

	public Graph(int N) { // 생성자
		this.nodes = new Node[N + 1]; // 정점들을 저장할 배열 생성
		for(int i = 1; i < N + 1; i++) {
			nodes[i] = new Node(i);
		}
	}

	public void add(int v1, int v2) { // 두 정점을 양방향 연결
		Node n1 = nodes[v1];
		Node n2 = nodes[v2];

		if (!n1.adjacent.contains(n2))
			n1.adjacent.add(n2);
		if (!n2.adjacent.contains(n1))
			n2.adjacent.add(n1);
	}

	public void DFS(Node root) {
		root.visited = true;
		System.out.print(root.data + " ");
		for(Node n : root.adjacent) {
			if(!n.visited) {
				DFS(n); // DFS메소드를 재귀적으로 호출
			}
		}
	}
	
	public void BFS(Node root) {
		Queue<Node> que = new LinkedList<>();
		que.add(root);
		while(!que.isEmpty()) {
			Node n = que.remove();
			n.visited = true;
			for(Node adj : n.adjacent) { // 큐에서 뺀 정점과 인접한 정점을 모두 큐에 넣
				if(!adj.visited) {
					que.add(adj);
					adj.visited = true; // 같은 정점 중복 추가를 방지
				}
			}
			System.out.print(n.data + " ");
		}
	}
}	