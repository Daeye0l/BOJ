package tree;

import java.io.*;
import java.util.*;

public class TREE20955 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n+1];
		
		int cutCnt = 0; // 사이클을 끊은 횟수
		for(int i = 1; i <= n; i++) parent[i] = i;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			// 두 노드가 이미 같은 집합에 속해 있는 경우
			if(!union(u, v)) {
				// 사이클을 끊어야 함
				cutCnt++;
			}
		}	
		
		// 서로 다른 집합 n개를 연결하기 위해서는 n-1번의 연결 연산 + 사이클을 끊은 연산 횟수
		System.out.println(getGroupCnt()-1 + cutCnt);
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
		// 각 집합의 서로 다른 루트만 rootSet에 저장
		for(int i = 1; i < parent.length; i++) {
			rootSet.add(findRoot(i));
		}
		// 집합이 갯수 반환
		return rootSet.size();
	}
}