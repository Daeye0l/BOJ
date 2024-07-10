package ds;

import java.io.*;
import java.util.*;

public class DS1717 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 각 노드의 부모를 저장할 배열, 초기에는 자기 자신으로 부모로 저장
		parent = new int[n+1];
		for(int i = 0; i < n+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int calc = Integer.parseInt(st.nextToken());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			if(calc == 0) { // 두 집합을 합침
				unionSet(node1, node2);
			}
			else { // 두 원소가 같은 집합에 포함되어 있는지 확인
				if(findRoot(node1) != findRoot(node2)) {
					System.out.println("NO");
				}
				else System.out.println("YES");
			}
		}
	}
	
	// 특정 노드의 루트를 찾을 때까지 재귀 수행
	private static int findRoot (int node) {
		if(parent[node] != node) {
			return findRoot(parent[node]);
		}
		else return node;
	}
	
	// 두 노드의 루트를 찾아 다른 경우 합침
	private static void unionSet(int node1, int node2) {
		int root1 = findRoot(node1);
		int root2 = findRoot(node2);
		if(root1 != root2) {
			parent[root2] = root1;
		}
	}
}