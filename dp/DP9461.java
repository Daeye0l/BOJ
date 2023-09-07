package dp;

import java.io.*;

public class DP9461 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력받기

		
		long[] dp = new long[101]; // 피도반 수열을 저장할 배열 생성
		// 수열의 첫 5개 피도반 수를 초기화
		dp[1] = 1; dp[2] = 1; dp[3] = 1; dp[4] = 2; dp[5] = 2; 
		// 6번째 피도반 수부터 100번째 피도반 수까지 구하기
		for(int i = 6; i < 101; i++) {
			dp[i] = dp[i-5] + dp[i-1];
		}
		
		int n = 0;
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
}