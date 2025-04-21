package dp;

import java.io.*;
import java.util.*;

public class DP7579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] memory = new int[n+1];
		int[] cost = new int[n+1];
		
		// 각 앱이 사용하는 메모리와 비활성화 비용을 저장
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n+1][n*100+1]; // i 번째 앱까지 사용하여 j 비용을 만드는 최대 메모리 크기를 저장
		int minCost = Integer.MAX_VALUE; // 비활성화의 최소 비용
		for(int i = 1; i <= n; i++) {
			for(int j = 0; j <= n*100; j++) {
				if(j < cost[i]) { // i 번째 앱을 선택할 수 없는 경우
					dp[i][j] = dp[i-1][j];
				}
				else {
					// i 번째 앱을 선택하지 않은 경우의 최대 메모리 크기와 선택한 경우의 최대 메모리 크기 중 더 큰 경우를 저장
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-cost[i]] + memory[i]);
				}
				if(dp[i][j] >= m) { // 필요한 메모리가 확보 됐다면 최솟값 갱신
					minCost = Math.min(minCost, j);
				}
			}
		}
		
		System.out.println(minCost);
	}
}