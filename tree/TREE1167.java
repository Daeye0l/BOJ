package tree;

import java.io.*;
import java.util.*;

public class TREE1167 {
	static List<List<Node>> tree;
	static boolean[] visited; // 정점 방문여부 저장
	static int max = 0; // 한 정점으로부터 가장 멀리 있는 정점까지의 거리
	static int startNumber = 0; // 지름을 구할 때의 시작점이 될 정점 번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int v = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		visited = new boolean[v+1];
	
		for(int i = 0; i < v+1; i++) {
			tree.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < v; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nodeNumber = Integer.parseInt(st.nextToken());
			while(true) {
				int value = Integer.parseInt(st.nextToken());
				if(value == -1) break;
				int cost = Integer.parseInt(st.nextToken());
				tree.get(nodeNumber).add(new Node(value, cost));
			}
		}	
		
		dfs(1, 0); // 임의의 정점으로부터 가장 멀리 있는 정점의 번호 구하기
		visited = new boolean[v+1];
		dfs(startNumber, 0); // 구한 정점으로부터 지름 구하기
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
		
		// 거리의 누적합이 최대치를 갱신할 때마다 시작점 노드 번호 변경
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