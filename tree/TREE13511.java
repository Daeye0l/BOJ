package tree;

import java.io.*;
import java.util.*;

public class TREE13511 {
	static int maxCnt = 100000;
	static int maxLog = 16;
	static List<List<Node>> edges = new ArrayList<>();
	static int[][] parent;
	static long[][] weight;
	static int[] level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[maxCnt+1][maxLog+1]; // n번 노드의 2^k번째 부모를 저장 
		weight = new long[maxCnt+1][maxLog+1]; // n번 노드로부터 2^k번째 부모까지의 비용을 저장
		level = new int[maxCnt+1]; // n번 노드의 레벨을 저장
		
		// 트리의 에지를 입력받아 저장
		for(int i = 0; i <= N; i++) edges.add(new ArrayList<>());
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			edges.get(u).add(new Node(v, w));
			edges.get(v).add(new Node(u, w));
		}
		
		makeTree(1, 1, 1, 0);
		calcParentAndWeight(N);
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			
			// 1번 쿼리인 경우
			if(q == 1) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				sb.append(getQueryOne(u, v)).append("\n");
			}
			
			// 2번 쿼리인 경우
			else {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				sb.append(getQueryTwo(u, v, k)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void makeTree(int curr, int p, int l, int w) {
		// 트리의 모든 노드에 대해 부모 번호, 레벨, 부모까지의 비용을 저장
		parent[curr][0] = p;
		level[curr] = l;
		weight[curr][0] = w;
		
		for(Node child : edges.get(curr)) {
			if(child.num != p) {
				makeTree(child.num, curr, l+1, child.w);
			}
		}
	}
	
	private static void calcParentAndWeight(int N) {
		// 모든 노드에 대해 가까운 조상부터 올라가면서 계산
		for(int k = 1; k <= maxLog; k++) {
			for(int n = 1; n <= N; n++) {
				parent[n][k] = parent[parent[n][k-1]][k-1];
				weight[n][k] = weight[n][k-1] + weight[parent[n][k-1]][k-1];
			}
		}
	}
	
	private static int getLca(int u, int v) {
		// 둘중 더 깊은 노드가 u가 되도록 조정
		if(level[u] < level[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		
		// 두 노드의 높이가 같아지도록 조정
		int diff = level[u]-level[v];
		for(int k = maxLog; k >= 0; k--) {
			if(diff >= (1 << k)) {
				u = parent[u][k];
				diff -= (1 << k);
			}
		}
		
		// 한 노드가 다른 노드의 조상이었던 경우
		if(u == v) return u;
		
		// 공통 조상 찾기
		for(int k = maxLog; k >= 0; k--) {
			if(parent[u][k] != parent[v][k]) {
				u = parent[u][k];
				v = parent[v][k];
			}
		}
		
		// 공통 조상 반환
		return parent[u][0];
	}
	
	private static long getQueryOne(int u, int v) {
		int lca = getLca(u, v);
		long result = 0;
	
		// u -> lca의 비용 누적하기
		int diff = level[u]-level[lca];
		for(int i = maxLog; i >= 0; i--) {
			if(diff >= (1 << i)) {
				result += weight[u][i];
				u = parent[u][i];
				diff -= (1 << i);
			}
		}
		
		// lca -> v의 비용 누적하기
		diff = level[v]-level[lca];
		for(int i = maxLog; i >= 0; i--) {
			if(diff >= (1 << i)) {
				result += weight[v][i];
				v = parent[v][i];
				diff -= (1 << i);
			}
		}
	
		return result;
	}
	
	private static int getQueryTwo(int u, int v, int k) {
		int lca = getLca(u, v);
		int result = 0;
		
		// k번째 노드가 u -> lca 경로상에 존재하는 경우
		if(level[u]-level[lca]+1 >= k) {
			// k번째 노드는 u의 k-1번째 조상임
			int diff = k-1;
			for(int i = maxLog; i >= 0; i--) {
				if(diff >= (1 << i)) {
					u = parent[u][i];
					diff -= (1 << i);
				}
			}
			result = u;
		}
		
		// k번째 노드가 lca -> v 경로상에 존재하는 경우
		else {
			// k번째 노드는 v의 (전체 길이 - (k-1))번째 조상임
			int diff = (level[u]-level[lca])+(level[v]-level[lca]) - (k-1);
			for(int i = maxLog; i >= 0; i--) {
				if(diff >= (1 << i)) {
					v = parent[v][i];
					diff -= (1 << i);
				}
			}
			result = v;
		}
		
		return result;
	}
	
	private static class Node {
		private int num;
		private int w;
		
		Node(int num, int w) {
			this.num = num;
			this.w = w;
		}
	}
}