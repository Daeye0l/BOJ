package dp;

import java.io.*;
import java.util.*;

public class DP11049 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n+1][n+1]; // dp[i][j] = i행렬 ~ j행렬 까지를 곱할 때 최소 연산 횟수
		int[] d = new int[n+1];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			d[i] = Integer.parseInt(st.nextToken());
			d[i+1] = Integer.parseInt(st.nextToken());
		}
		
		// 행렬이 1개인 경우 곱셈이 이뤄지지 않으므로 0을 채움
		for(int i = 1; i <= n; i++) {
			dp[i][i] = 0;
		}
		for(int cnt = 2; cnt <= n; cnt++) { // 연쇄적으로 곱하는 행렬의 갯수는 2개 ~ n개까지 가능
			for(int i = 1; i <= n-cnt+1; i++) { // i개의 행렬을 곱할 때 채워야 하는 dp칸의 수(n-1개 ~ 1개)
				int j = i + (cnt-1);
				
				dp[i][j] = Integer.MAX_VALUE;
				for(int k = i; k < j; k++) {
					// i ~ j 행렬까지의 곱을 i ~ k, k+1 ~ j로 분할
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + d[i-1]*d[k]*d[j]);
				}
			}
		}
		
		System.out.println(dp[1][n]); // 1 ~ n번 행렬을 곱할 때 최소 연산 횟수
	}
}