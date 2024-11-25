package tree;

import java.io.*;
import java.util.*;

public class TREE11437 {
	static int[] parents, levels;
	static List<List<Integer>> connect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		levels = new int[n+1];
		connect = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			connect.add(new ArrayList<>());
		}
		for(int i = 0; i < n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect.get(a).add(b);
			connect.get(b).add(a);
		}
		
		makeTree(1, 0, 1);
		
		int m = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void makeTree(int currentNode, int parent, int level) {
		// 현재 정점의 레벨과 부모를 설정한 후 인접한 정점으로 재귀
		levels[currentNode] = level;
		parents[currentNode] = parent;
		for(int node : connect.get(currentNode)) {
			if(node != parent) {
				makeTree(node, currentNode, level+1);
			}
		}
	}

	private static int lca(int a, int b) {
		// 두 정점의 레벨을 동일하게 만들기
		while(levels[a] != levels[b]) {
			if(levels[a] > levels[b]) {
				a = parents[a];
			}
			else {
				b = parents[b];
			}
		}
		// 레벨이 같아졌다면 최소 공통 조상 찾기
		while(a != b) {
			a = parents[a];
			b = parents[b];
		}
		return a;
	}
}