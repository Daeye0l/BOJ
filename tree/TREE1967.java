package tree;

import java.io.*;
import java.util.*;

public class TREE1967 {
	static List<List<Node>> tree;
	static boolean[] visited;
	static int startNumber = 0;
	static int max = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		visited = new boolean[n+1];
		
		for(int i = 0; i < n+1; i++) {
			tree.add(new ArrayList<Node>());
		}
		
		// 부모, 자식의 정점 번호와 가중치를 받아 연관
		for(int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parentNumber = Integer.parseInt(st.nextToken());
			int childNumber = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree.get(parentNumber).add(new Node(childNumber, cost));
			tree.get(childNumber).add(new Node(parentNumber, cost));
		}
		
		dfs(1, 0); // 임의의 정점으로부터 dfs 탐색을 하여 startNumber 초기화
		visited = new boolean[n+1];
		dfs(startNumber, 0); // startNumber노드로 부터 가장 멀리 있는 노드까지의 거리(지름) 구하기
		
		System.out.println(max);
	}
	
	private static class Node {
		int value;
		int cost;
		
		public Node(int value, int cost) {
			this.value = value;
			this.cost = cost;
		}
	}
	
	private static void dfs(int nodeNumber, int costSum) {
		visited[nodeNumber] = true;
		
		// 거리의 누적합이 최대값을 갱신할 때마다 시작 노드 번호 변경
		if(costSum > max) {
			max = costSum;
			startNumber = nodeNumber;
		}
		
		for(Node node : tree.get(nodeNumber)) {
			if(!visited[node.value]) {
				dfs(node.value, costSum + node.cost);
			}
		}
	}
}