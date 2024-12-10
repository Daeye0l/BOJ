package graph;

import java.io.*;
import java.util.*;

public class GRA1956 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[][] distance = new int[v+1][v+1];
		int max = 4000000;
		
		for(int i = 1; i <= v; i++) {
			for(int j = 1; j <= v; j++) {
				if(i == j) distance[i][j] = 0;
				else distance[i][j] = max;
			}
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			distance[a][b] = c;
		}
		
		for(int k = 1; k <= v; k++) {
			for(int i = 1; i <= v; i++) {
				for(int j = 1; j <= v; j++) {
					distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
				}
			}
		}
		
		int result = max * 2;
		for(int i = 1; i <= v; i++) {
			for(int j = 1; j <= v; j++) {
				// i에서 j로 가는 경로와 j에서 i로 가는 경로가 존재하면 사이클을 이룸
				if(distance[i][j] != max && distance[j][i] != max) {
					if(i != j) result = Math.min(result, distance[i][j] + distance[j][i]);
				}
			}
		}
		
		if(result == max * 2) System.out.println(-1);
		else System.out.println(result);
	}
}