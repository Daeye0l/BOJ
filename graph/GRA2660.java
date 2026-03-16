package graph;

import java.io.*;
import java.util.*;

public class GRA2660 {
	static int[][] distance;
	static int INF = 10000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		distance = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i == j) distance[i][j] = 0;
				else distance[i][j] = INF;
			}
 		}
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(a == -1 && b == -1) break;
			
			distance[a][b] = 1;
			distance[b][a] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int a = 1; a <= N; a++) {
				for(int b = 1; b <= N; b++) {
					if(distance[a][b] > distance[a][k] + distance[k][b]) {
						distance[a][b] = distance[a][k] + distance[k][b];
					}
				}
			}
		}
		
		int[] score = new int[N+1];
		for(int i = 1; i <= N; i++) {
			int max = 0;
			for(int j = 1; j <= N; j++) {
				if(distance[i][j] == INF) continue;
				max = Math.max(max, distance[i][j]);
			}
			score[i] = max;
		}
		
		int minScore = INF;
		List<Integer> candidate = new ArrayList<>();
		for(int i = 1; i <= N; i++) {
			if(score[i] < minScore) {
				minScore = score[i];
				candidate.clear();
				candidate.add(i);
			}
			else if(score[i] == minScore) {
				candidate.add(i);
			}
		}
		
		StringBuilder result = new StringBuilder();
		result.append(minScore).append(" ").append(candidate.size()).append('\n');
		for(int c : candidate) {
			result.append(c).append(" ");
		}
		
		System.out.println(result);
	}
}