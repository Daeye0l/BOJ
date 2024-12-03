package tree;

import java.io.*;
import java.util.*;

public class TREE17073 {
	static boolean[] visited;
	static List<List<Integer>> connect = new ArrayList<>();
	static int leafCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		
		for(int i = 0; i <= n; i++) connect.add(new ArrayList<>());
		// 정점 u와 v의 연결관계 저장
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			connect.get(u).add(v);
			connect.get(v).add(u);
		}
		
		// makeTree로 트리를 구성하지 않아도 리프노드는 다른 정점과 연결된 간선이 1개임을 이용
		for(int i = 2; i <= n; i++) {
			if(connect.get(i).size() == 1) leafCnt++;
		}
		
		double result = (double)w / leafCnt;
		System.out.println(String.format("%.10f", result));
	}
	
	/*
	private static void makeTree(int curr, int p) {
		boolean isLeaf = true; // 모든 노드는 처음에 리프노드임
		visited[curr] = true;
		for(int next : connect.get(curr)) {
			if(!visited[next]) {
				// 자식노드로 재귀하게 되면서 더 이상 리프노드가 아님
				isLeaf = false;
				makeTree(next, curr);
			}
		}
		if(isLeaf) leafCnt++;
	}
	*/
}