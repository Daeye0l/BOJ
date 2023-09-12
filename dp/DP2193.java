package dp;

import java.io.*;

public class DP2193 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 자릿수 입력받기
		long[] dp = new long[n+1]; // 자릿수가 n일 때 이친수의 개수를 저장할 자료구조
		
		for(int i = 1; i <= n; i++) {
			if(i == 1) dp[i] = 1; // 자릿수가 1일 때 이친수는 1 하나
			else if(i == 2) dp[i] = 1; // 자릿수가 2일 때 이친수는 10 하나
			else {
				dp[i] = dp[i-1] + dp[i-2];
			}
		}
		
		System.out.print(dp[n]);
	}
}