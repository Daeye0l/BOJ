package tree;

import java.io.*;
import java.util.*;

public class TREE11438 {
	static List<List<Integer>> edges = new ArrayList<>();
	static int maxNodeCnt = 100000; // 노드의 최대 개수
	static int maxNodeLog = 16; // 노드의 최대 개수가 100000개 -> 2^17 = 131072보다 작으므로 2^16이하의 숫자로 조합가능
	static int[][] parent;
	static int[] level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// parent[n][k]: n번 노드의 2^k번째 조상
		parent = new int[maxNodeCnt+1][maxNodeLog+1];
		level = new int[maxNodeCnt+1];
		
		// 트리의 에지를 입력받아 저장
		for(int i = 0; i <= N; i++) edges.add(new ArrayList<>());
		for(int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			edges.get(x).add(y);
			edges.get(y).add(x);
		}
		
		// 루트노드가 1인 트리 구성
		makeTree(1, 1, 1);
		// 모든 노드의 부모노드 구하기
		getParent();
		
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			sb.append(getLca(x, y)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void makeTree(int curr, int p, int l) {
		parent[curr][0] = p; // 모든 노드에 대해 직계 부모만 우선적으로 저장
		level[curr] = l; // 레벨 저장
		
		for(int child : edges.get(curr)) {
			if(child != p) {
				makeTree(child, curr, l+1);
			}
		}
	}
	
	private static void getParent() {
		// n번 노드의 2^k번째 조상 구하기
		for(int k = 1; k <= maxNodeLog; k++) {
			// n번 노드와 가까운 조상부터 바텀업 방식으로 구해서 저장
			for(int n = 1; n <= maxNodeCnt; n++) {
				parent[n][k] = parent[parent[n][k-1]][k-1];
			}
		}
	}
	
	private static int getLca(int x, int y) {
		// x의 깊이가 더 깊은 상태가 되도록 조정
		if(level[x] < level[y]) {
			int temp = x;
			x = y;
			y = temp;
		}
		
		// 두 노드의 높이가 같아지도록 조정(diff가 0이 될 때까지)
		int diff = level[x]-level[y];
		for(int k = maxNodeLog; k >= 0; k--) {
			if(diff >= (1 << k)) {
				x = parent[x][k];
				diff -= (1 << k);
			}
		}
		
		// 한 노드가 다른 노드의 조상이었던 경우
		if(x == y) return x;
		
		// 높이가 같아졌으므로 공통조상을 찾을 때까지 거슬러 올라가기
		for(int k = maxNodeLog; k >= 0; k--) {
			if(parent[x][k] != parent[y][k]) {
				x = parent[x][k];
				y = parent[y][k];
			}
		}
		
		// 공통 조상을 반환
		return parent[x][0];
	}
}