package graph;

import java.io.*;
import java.util.*;

public class GRA1922 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] graph = new int[m][3];
		parent = new int[n+1];
		
		// 컴퓨터 a, b와 연결하는 비용 c를 입력 받아 graph에 저장
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph[i][0] = a;
			graph[i][1] = b;
			graph[i][2] = c;
		}
		
		// 두 컴퓨터를 연결하는 비용이 오름차순이 되도록 정렬
		Arrays.sort(graph, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
		});
		
		for(int i = 1; i <= n; i++) parent[i] = i;
		int cost = 0;
		for(int i = 0; i < m; i++) {
			int a = graph[i][0];
			int b = graph[i][1];
			int c = graph[i][2];
			// 병합에 성공한 경우만 비용 누적
			if(union(a, b)) {
				cost += c;
			}
		}
		
		System.out.println(cost);
	}
	
	private static boolean union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		// 두 노드가 서로 다른 집합이라면 병합 후 true 반환
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
			return true;
		}
		// 두 노드가 서로 같은 집합이라면 false 반환
		return false;
	}
	
	private static int findRoot(int x) {
		// 부모 노드와 자신이 같은 번호라면 루트 노드임
		if(parent[x] == x) {
			return x;
		}
		// 루트 노드를 찾으러 가는 경로상에 있는 노드들의 부모를 모두 루트 노드로 바꿔 경로 압축
		parent[x] = findRoot(parent[x]);
		return parent[x];
	}
}