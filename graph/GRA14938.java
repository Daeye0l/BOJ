package graph;

import java.io.*;
import java.util.*;

public class GRA14938 {
	static int n, m ,r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		int[] itemCnt = new int[n+1];
		int[][] distance = new int[n+1][n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			itemCnt[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) distance[i][j] = 0;
				else distance[i][j] = 1500;
			}
		}
		
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			distance[a][b] = l;
			distance[b][a] = l;
		}
		
		floydWarshall(distance); // 모든 지역에 대해 다른 지역까지의 최소 거리를 구함
		System.out.println(getMaxItemCnt(distance, itemCnt));
	}
	
	private static void floydWarshall(int[][] distance) {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
	}
	
	private static int getMaxItemCnt(int[][] distance, int[] itemCnt) {
		int max = 0;
		for(int i = 1; i <= n; i++) {
			int tmp = itemCnt[i];
			for(int j = 1; j <= n; j++) {
				// 예은이의 수색범위 안에 있는 지역의 아이템만 누적
				if(i != j && distance[i][j] <= m) {
					tmp += itemCnt[j];
				}
			}
			max = Math.max(max, tmp);
		}
		return max;
	}
}