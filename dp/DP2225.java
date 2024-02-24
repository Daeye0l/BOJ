package dp;

import java.io.*;
import java.util.*;

public class DP2225 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 만들어야 할 정수
		int k = Integer.parseInt(st.nextToken()); // 합으로 표현할 정수의 개수
		int[][] dp = new int[n+1][k+1]; // 0 ~ n까지의 정수를 k개 사용하여 n을 만드는 경우의 수를 저장
		
		for(int i = 0; i <= n; i++) {
			for(int j = 1; j <= k; j++) {
				if(i == 0) {
					dp[i][j] = 1; // 정수 0을 k개의 합으로 표현하는 경우의 수는 무조건 한 개
				}
				else { // 0을 제외한 1 ~ n의 정수에 대해서
					for(int l = 1; l <= j; l++) {
						dp[i][j] = (dp[i][j] + dp[i-1][l]) % 1000000000; // 정수 i를 j개로 표현하는 경우의 수는 i-1을 1~j개로 표현하는 경우의 수와 같다.
					}
				}
			}
		}
		
		System.out.println(dp[n][k] % 1000000000);
	}
}