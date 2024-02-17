package dp;

import java.io.*;
import java.util.*;

public class DP11057 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+2][10];
		
		// j로 시작하는 i자리 오르막수 채워넣기
		for(int i = 1; i <= n+1; i++) {
			for(int j = 0; j < 10; j++) {
				// 한자릿수 오르막수의 개수 초기화
				if(i == 1) {
					dp[i][j] = 1;
				}
				else {
					int k = j;
					while(k < 10) {
						dp[i][j] += dp[i-1][k] % 10007;
						k++;
					}
				}
			}
		}
		
		System.out.println(dp[n+1][0] % 10007);
	}
}