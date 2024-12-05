package graph;

import java.io.*;
import java.util.*;

public class GRA11403 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] adjMatrix = new int[n+1][n+1];
		
		// i에서 j로 가는 경로가 있는 경우 1을, 없는 경우 0을 저장
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// i에서 j로 가기 전 거쳐가는 k에 대해 i에서 k로 이동할 수 있고 k에서 j로 이동할 수 있다면
		// i에서 j로 이동하는 경로도 존재함
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(adjMatrix[i][k] == 1 && adjMatrix[k][j] == 1) {
						adjMatrix[i][j] = 1;
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				sb.append(adjMatrix[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}