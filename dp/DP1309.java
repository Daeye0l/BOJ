package dp;

import java.io.*;

public class DP1309 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[100001];
		
		dp[0] = 1;
		for(int i = 1; i < 100001; i++) {
			if(i == 1) dp[i] = 3;
			else dp[i] = (dp[i-1]*2 + dp[i-2]) % 9901; 
		}
		
		System.out.println(dp[n]);
	}
}