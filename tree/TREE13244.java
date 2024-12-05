package tree;

import java.io.*;
import java.util.*;

public class TREE13244 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int m = Integer.parseInt(br.readLine());
			boolean isCycle = false;
			parent = new int[n+1];
			
			for(int j = 1; j <= n; j++) parent[j] = j;
			for(int j = 0; j < m; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				// 사이클이 생긴 경우 종료
				if(!union(u, v)) {
					isCycle = true;
				}
			}
			
			// 사이클이 없고 모든 노드가 한 집합에 속해 있는 경우 트리
			if(!isCycle && getGroupCnt() == 1) sb.append("tree").append("\n");
			// 사이클이 있거나 노드가 속한 집합이 2개 이상이라면 그래프(트리는 A노드에서 시작해서 B노드로 가는 경로가 무조건 존재)
			else sb.append("graph").append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		// 두 노드가 같은 집합에 속해있지 않다면 병합 후 true 반환
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
			return true;
		}
		return false;
	}
	
	private static int findRoot(int x) {
		// 한 노드에 대해 루트노드를 찾으러 가는 경로상에 있는 모든 노드의 부모를 루트노드로 변경(경로 압축)
		if(parent[x] != x) {
			parent[x] = findRoot(parent[x]);
		}
		// 최종적으로 루트노드 반환
		return parent[x];
	}
	
	private static int getGroupCnt() {
		Set<Integer> rootSet = new HashSet<>();
		for(int i = 1; i < parent.length; i++) {
			rootSet.add(findRoot(i));
		}
		return rootSet.size();
	}
}