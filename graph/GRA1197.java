package graph;

import java.io.*;
import java.util.*;

public class GRA1197 {
	static int[] minCost;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[][] graph = new int[e][3]; // 두 노드와 간선의 가중치를 저장
		parent = new int[v+1]; // 각 노드의 부모 노드를 저장
		
		// 모든 정점의 처음 부모 노드는 자기 자신
		for(int i = 1; i <= v; i++) {
			parent[i] = i;
		}
		
		// 두 노드 번호와 간선 가중치에 대한 정보를 저장
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 간선 가중치에 대하여 오름차순으로 정렬
		Arrays.sort(graph, new Comparator<int[]>() {
			public int compare(int[] g1, int[] g2) {
				return g1[2] - g2[2];
			}
		});
		
		int minCost = 0;
		for(int i = 0; i < e; i++) {
			// 두 노드의 루트 노드가 다르다면 서로 다른 집합에 속해 사이클이 생기지 않으므로 선택
			if(findRoot(graph[i][0]) != findRoot(graph[i][1])) {
				union(graph[i][0], graph[i][1]); // 병합
				minCost += graph[i][2]; // 가중치 누적
			}
		}
		
		System.out.println(minCost);
	}
	
	// 루트 노드의 번호를 찾아서 반환
	private static int findRoot(int x) {
		// 자기 자신의 번호가 루트 노드의 번호와 같다는 것은 루트 노드라는 뜻
		if(x == parent[x]) {
			return x;
		}
		else {
			// 부모에 대해 재귀 수행을 통해 루트 노드를 찾아
			int root = findRoot(parent[x]);
			// 경로상 모든 노드의 부모를 루트 노드로 바꿔 경로 압축
			parent[x] = root;
			return root;
		}
	}
	
	// 두 노드의 루트를 찾아서 병합
	private static void union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
	
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
		}
	}
}