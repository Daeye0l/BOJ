package graph;

import java.io.*;
import java.util.*;

public class GRA10021 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken()); // 파이프 설치를 위한 최소 비용
		int[][] points = new int[n+1][2]; // n개의 포인트를 저장
		parent = new int[n+1];
		
		// 각 포인트의 처음 부모는 자기 자신 
		for(int i = 1; i <= n; i++) parent[i] = i;
		// n개의 포인트를 입력받아 저장
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new int[] {x, y};
		}
		
		// 두 포인트 사이의 거리가 작은 순서대로 우선순위 큐 정렬
		PriorityQueue<Connect> que = new PriorityQueue<>(new Comparator<Connect>() {
			public int compare(Connect n1, Connect n2) {
				return n1.distance - n2.distance;
			}
		});
		// 포인트들의 모든 조합에 대해 거리를 구하고 최소 비용 조건을 충족하면 큐에 삽입
		for(int i = 1; i <= n; i++) {
			for(int j = i+1; j <= n; j++) {
				int distance = getDistance(points[i][0], points[i][1], points[j][0], points[j][1]);
				if(distance >= c) {
					que.offer(new Connect(i, j, distance));	
				}
			}
		}

		int minCost = 0;
		int connectCnt = 0;
		// 큐에 있는 연결관계를 탐색하면서
		while(!que.isEmpty()) {
			Connect connect = que.poll();
			// 두 포인트가 병합되었다면
			boolean ret = union(connect.start, connect.end);
			if(ret) {
				// 비용을 누적
				minCost += connect.distance;
				connectCnt++;
			}
		}
		
		if(connectCnt == n-1) System.out.println(minCost);
		else System.out.println(-1);
	}
	
	private static int getDistance(int x1, int y1, int x2, int y2) {
		int distance = (int)Math.pow(x1-x2, 2) + (int)Math.pow(y1-y2, 2);
		return distance;
	}
	
	private static int findRoot(int x) {
		if(parent[x] == x) {
			return x;
		}
		parent[x] = findRoot(parent[x]);
		return parent[x];
	}
	
	private static boolean union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
			return true;
		}
		return false;
	}
	
	private static class Connect {
		private int start;
		private int end;
		private int distance;
		
		Connect(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}
	}
}