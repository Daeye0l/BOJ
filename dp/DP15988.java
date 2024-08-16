package dp;

import java.io.*;

public class DP15988 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[] dp = new long[1000001];
		for(int i = 0; i < 1000001; i++) {
			if(i == 0) dp[i] = 1;
			else if(i == 1 || i == 2) dp[i] = i;
			else dp[i] = (dp[i-3] + dp[i-2] + dp[i-1]) % 1000000009;
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
}