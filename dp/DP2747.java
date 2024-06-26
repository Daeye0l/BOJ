package dp;

import java.io.*;

public class DP2747 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[46];
		dp[0] = 0;
		dp[1] = 1;
		
		for(int i = 2; i < 46; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		System.out.println(dp[n]);
	}
}