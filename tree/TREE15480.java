package tree;

import java.io.*;
import java.util.*;

public class TREE15480 {
	static List<List<Integer>> edges = new ArrayList<>();
	static int[][] parent;
	static int[] level;
	static int maxCnt = 100000;
	static int maxLog = 16;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[maxCnt+1][maxLog+1];
		level = new int[maxCnt+1];
		
		// 트리의 에지를 입력받아 저장
		for(int i = 0; i <= N; i++) edges.add(new ArrayList<>());
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			edges.get(u).add(v);
			edges.get(v).add(u);
		}
		
		makeTree(1, 1, 1);
		saveParent();
		
		// M개의 쿼리 결과를 저장
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			int result = getLca(u, v);
			int c1 = getLca(u, r);
			int c2 = getLca(v, r);
			if(level[c1] > level[c2]) {
				result = level[result] > level[c1] ? result : c1; 
			}
			else {
				result = level[result] > level[c2] ? result : c2;
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void makeTree(int curr, int p, int l) {
		parent[curr][0] = p;
		level[curr] = l;
		for(int child : edges.get(curr)) {
			if(child != p) {
				makeTree(child, curr, l+1);
			}
		}
	}
	
	private static void saveParent() {
		for(int i = 1; i <= maxLog; i++) {
			for(int j = 1; j <= maxCnt; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
	}
	
	private static int getLca(int u, int v) {		
		// 두 노드의 레벨이 같아지도록 조정
		if(level[u] < level[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		for(int i = maxLog; i >= 0; i--) {
			if(level[u]-level[v] >= (1 << i)) {
				u = parent[u][i];
			}
		}
		
		// 한 노드가 다른 노드의 조상인 경우
		if(u == v) return u;
		
		// 올라가면서 공통 조상 찾기
		for(int i = maxLog; i >= 0; i--) {
			if(parent[u][i] != parent[v][i]) {
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		
		return parent[u][0];
	}
}