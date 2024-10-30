package graph;

import java.io.*;
import java.util.*;

public class GRA1976 {
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		int[] route = new int[m];
		
		for(int i = 1; i <= n; i++) parent[i] = i;
		for(int i = 1; i <= n; i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				int state = Integer.parseInt(st.nextToken());
				// i번 도시와 j번 도시가 연결되어 있다면 병합
				if(state == 1) {
					union(i, j);
				}
			}
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 여행 루트 저장
		for(int i = 0; i < m; i++) route[i] = Integer.parseInt(st.nextToken());
		// 여행 루트 중 하나라도 같은 집합에 속하지 않은 도시가 있다면 불가능
		for(int i = 0; i < m-1; i++) {
			/* 최종적으로 parent 배열의 값으로 비교하면 안되는 이유는 경로 압축을 해도
			   만약 서로 다른 분리 집합을 결합하게 되면 결합되는 쪽의 하위 원소들은
			   여전히 루트를 가키지 않을 수 있기 때문 */
			if(findRoot(route[i]) != findRoot(route[i+1])) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}
	
	private static void union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		// 두 노드가 같은 집합이 아닌 경우만 병합
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
		}
	}
	
	private static int findRoot(int x) {
		if(parent[x] == x) {
			return x;
		}
		// 한 노드에서 루트 노드까지의 경로 상에 있는 모든 노드의 부모를 루트로 변경
		parent[x] = findRoot(parent[x]);
		return parent[x];
	}
}