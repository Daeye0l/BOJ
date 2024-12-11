package tree;

import java.io.*;
import java.util.*;

public class TREE15681 {
	static int[] parents;
	static int[] size;
	static List<List<Integer>> connect;
	static List<List<Integer>> currentNodeChild;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		size = new int[n+1];
		connect = new ArrayList<>();
		currentNodeChild = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			parents[i] = i;
			connect.add(new ArrayList<>());
			currentNodeChild.add(new ArrayList<>());
		}
		// 두 정점을 입력 받아 connect 구성
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			connect.get(u).add(v);
			connect.get(v).add(u);
		}
		
		// r을 루트로 하는 트리를 구성하고각 정점을 루트로 하는 서브트리에 속한 정점의 수 구하기
		makeTree(r, -1);
		countSubtreeNode(r);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			int u = Integer.parseInt(br.readLine());
			sb.append(size[u]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	// parent를 currentNode의 부모로 하는 트리 구성
	private static void makeTree(int currentNode, int parent) { 
		for(int node : connect.get(currentNode)) {
			if(node != parent) {
				currentNodeChild.get(currentNode).add(node);
				parents[node] = currentNode;
				makeTree(node, currentNode);
			}
		}
	}
	
	// curretNode를 기준으로 서브트리에 속한 정점의 수 구하기
	private static void countSubtreeNode(int currentNode) {
		size[currentNode] = 1;
		for(int node : currentNodeChild.get(currentNode)) {
			countSubtreeNode(node);
			size[currentNode] += size[node];
		}
	}
}