package tree;

import java.io.*;
import java.util.*;

public class TREE3584 {
	static int n;
	static int[] parent, level;
	static List<List<Integer>> childNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			parent = new int[n+1];
			level = new int[n+1];
			childNode = new ArrayList<>();
			
			for(int j = 0; j <= n; j++) {
				parent[j] = j;
				childNode.add(new ArrayList<>());
			}
			for(int j = 0; j < n-1; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				parent[b] = a; // b 노드의 부모를 a 노드로 지정
				childNode.get(a).add(b); // a 노드의 자식으로 b 노드 추가
			}
			
			// 루트 노드를 기준으로 나머지 노드들 레벨 초기화 
			int root = getRoot();
			setLevel(root, 1);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int getRoot() {
		// 부모 노드의 번호가 자기 자신인 경우 루트
		for(int i = 1; i <= n; i++) {
			if(i == parent[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private static void setLevel(int curr, int l) {
		// 현재 노드의 레벨을 초기화 후 자식 노드로 재귀
		level[curr] = l;
		for(int next : childNode.get(curr)) {
			setLevel(next, l+1);
		}
	}
	
	private static int lca(int a, int b) {
		// 두 노드의 레벨을 동일하게 만들고
		while(level[a] != level[b]) {
			if(level[a] > level[b]) {
				a = parent[a];
			}
			else {
				b = parent[b];
			}
		}
		// 최소 공통 조상을 찾아 반환
		while(a != b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
	}
}