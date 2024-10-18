package graph;

import java.io.*;
import java.util.*;

public class GRA11404 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 도시의 갯수
		int m = Integer.parseInt(br.readLine()); // 노선의 갯수
		int[][] distance = new int[n+1][n+1]; // 각 도시에서 다른 도시로 가는 최소 비용
		
		for(int i = 1; i <= n; i++ ) {
			for(int j = 1; j <= n; j++) {
				if(i == j) distance[i][j] = 0; // 자기 자신으로 가는 비용은 0
				else distance[i][j] = 9900001; // 나머지는 최대 비용으로 설정
			}
		}
		
		// 노선의 정보로 거리 다시 초기화(시작 도시와 도착 도시를 잇는 노선은 하나가 아닐 수 있기 때문에 최소 값 저장)
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			distance[a][b] = Math.min(c, distance[a][b]);
		}
		
		// 시작 도시 i에서 도착 도시 j로 가는 최소비용 구하기(k 도시를 거쳐가는 비용이 더 적다면 갱신) 
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++ ) {
			for(int j = 1; j <= n; j++) {
				if(distance[i][j] == 9900001) {
					sb.append(0).append(" ");
				}
				else {
					sb.append(distance[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}