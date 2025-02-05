package graph;

import java.io.*;
import java.util.*;

public class GRA1389 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] distance = new int[n+1][n+1];
		
		// 유저간 연결 거리 배열 초기화
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) distance[i][j] = 0;
				else distance[i][j] = 100;
			}
		}
		
		// 서로 친구인 유저 사이의 거리는 1
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			distance[a][b] = 1;
			distance[b][a] = 1;
		}
		
		// 두 유저간 거리를 최단 거리로 갱신
		for(int k = 1; k <= n; k++) {
			for(int a = 1; a <= n; a++) {
				for(int b = 1; b <= n; b++) {
					distance[a][b] = Math.min(distance[a][k] + distance[k][b], distance[a][b]);
				}
			}
		}
		
		int temp = Integer.MAX_VALUE;
		int result = 0; // 케빈 베이컨 수가 가장 작은 사람의 번호
		for(int i = 1; i <= n; i++) {
			int sum = 0;
			for(int j = 1; j <= n; j++) {
				sum += distance[i][j];
			}
			// 더 작은 케빈 베이컨 수를 가진 유저의 번호로 결과값 갱신
			if(sum < temp) { 
				temp = sum;
				result = i;
			}
		}
		
		System.out.println(result);
	}
}	