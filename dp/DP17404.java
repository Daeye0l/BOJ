package dp;

import java.io.*;
import java.util.*;

public class DP17404 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] cost = new int[n][3];
		int[][] dp = new int[n][3];
		int minCost = 1000001; // 모든 집을 칠하는 비용의 최솟값
		
		// 각각의 집을 칠하는 비용 저장
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 처음 집을 칠하는 경우를 3개로 나눔
		for(int start = 0; start < 3; start++) {
			dp[0][start] = cost[0][start];
			// 처음 색깔을 고정하기 위해 나머지 초기 dp값 초기화
			if(start == 0) {
				dp[0][1] = 1001;
				dp[0][2] = 1001;
			}
			else if(start == 1) {
				dp[0][0] = 1001;
				dp[0][2] = 1001;
			}
			else {
				dp[0][0] = 1001;
				dp[0][1] = 1001;
			}
			// n번째 집까지 칠하는 칠하는 최소 비용 구하기
			for(int i = 1; i < n; i++) {
				for(int j = 0; j < 3; j++) {
					if(j == 0) dp[i][j] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][j];
					else if(j == 1) dp[i][j] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][j];
					else dp[i][j] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][j];
				}	
			}
			// 최소 비용을 갱신할 때 처음 집과 마지막 집의 색은 같지 않아야 함
			if(start == 0) minCost = Math.min(minCost, Math.min(dp[n-1][1], dp[n-1][2]));
			else if(start == 1) minCost = Math.min(minCost, Math.min(dp[n-1][0], dp[n-1][2]));
			else minCost = Math.min(minCost, Math.min(dp[n-1][0], dp[n-1][1]));
		}
		
		System.out.println(minCost);
	}
}