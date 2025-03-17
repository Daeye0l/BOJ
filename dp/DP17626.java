package dp;

import java.io.*;

public class DP17626 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1]; // dp[n] = 제곱수들의 합이 n과 같게 되는 제곱수들의 최소 갯수		
		
		// 최소 갯수로 값을 갱신하기 위해 최댓값으로 배열 초기화
		for(int i = 1; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
		// 1~n까지 바텀업으로 진행
		for(int i = 1; i <= n; i++) {
			// 제곱수 사용은 n의 제곱근 이하인 값들만 사용 가능
			for(int j = 1; j*j <= i; j++) {
				// j^2을 사용했으므로 + 1
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
			}
		}
		
		System.out.println(dp[n]);
	}
}