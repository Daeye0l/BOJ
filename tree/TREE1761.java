package tree;

import java.io.*;
import java.util.*;

public class TREE1761 {
	static List<List<Node>> edges = new ArrayList<>();
	static int[][] parent;
	static int[][] distance;
	static int[] level;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		parent = new int[400001][16];
		distance = new int[40001][16];
		level = new int[N+1];
		
		// 초기화
		for(int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
		}
		
		// 두 노드의 연결관계를 저장
		for(int i = 0; i <= N; i++) edges.add(new ArrayList<>());
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			edges.get(x).add(new Node(y, d));
			edges.get(y).add(new Node(x, d));
		}
		
		makeTree(1, 1, 1, 0);
		getParent();
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			sb.append(getDistance(x, y)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void makeTree(int curr, int l, int p, int d) {
		level[curr] = l; // 레벨 저장
		parent[curr][0] = p; // 부모 저장
		distance[curr][0] = d; // 부모와의 거리 저장 
		
		// 모든 노드의 직계 부모를 저장
		for(Node child : edges.get(curr)) {
			if(child.num != p) {
				makeTree(child.num, l+1, curr, child.distance);
			}
		}
	}
	
	private static void getParent() {
		for(int k = 1; k <= 15; k++) {
			// 모든 노드에 대해 한 레벨을 완성하고 다음 레벨을 계산해야함
			for(int n = 1; n <= 40000; n++) {
				// n번 노드의 2^k번째 조상 구하기
				parent[n][k] = parent[parent[n][k-1]][k-1];
				// n번 노드의 2^k번째 조상까지의 거리 구하기
				distance[n][k] = distance[n][k-1]+distance[parent[n][k-1]][k-1];
			}
		}
	}
	
	private static int getDistance(int x, int y) {
		int result = 0;
		
		// x가 더 깊은 위치에 있도록 조정
		if(level[x] < level[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		// 두 노드의 높이가 같아지도록 조정
		int diff = level[x]-level[y];
		for(int i = 15; i >= 0; i--) {
			if(diff >= (1<<i)) {
				result += distance[x][i];
				x = parent[x][i];
				diff -= (1<<i);
			}
		}
		
		// 한 노드가 다른 노드의 조상이었던 경우
		if(x == y) return result;
		
		// 공통 조상을 찾아가면서 거리를 누적
		for(int i = 15; i >= 0; i--) {
			if(parent[x][i] != parent[y][i]) {
				result += distance[x][i];
				result += distance[y][i];
				
				x = parent[x][i];
				y = parent[y][i];
			}
		}
		result += distance[x][0];
		result += distance[y][0];
		
		return result;
	}
	
	private static class Node {
		private int num;
		private int distance;
		
		Node(int num, int distance) {
			this.num = num;
			this.distance = distance;
		}
	}
}