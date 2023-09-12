package dp;

import java.io.*;

public class DP10844 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 길이 n 입력받기
		long[][] dp = new long[n+1][10]; // 길이가 n이고 시작하는 수가 0 ~ 9까지 있을 때 가능한 계단수의 개수
		// 실제로 시작하는 수가 0인 경우는 없지만 동적 자료구조를 채우기 위해 계산
		
		for(int i = 1; i <= n; i++) { // 길이가 1 ~ n까지
			for(int j = 0; j < 10; j++) { // 시작하는 수를 0 ~ 9까지 
				if(i == 1) dp[i][j] = 1; // 길이가 1일 때는 가능한 계단수는 무조건 1개
				else {
					// 길이가 2이상이고 시작하는 수가 0인 경우는 dp[i-1][1]에 있는 값을 그대로 사용
					if(j == 0) dp[i][j] = dp[i-1][j+1] % 1000000000;
					// 길이가 2이상이고 시작하는 수가 9인 경우는 dp[i-1][8]에 있는 값을 그대로 사용
					else if(j == 9) dp[i][j] = dp[i-1][j-1] % 1000000000;
					// 길이가 2이상이고 시작하는 수가 1 ~ 8인 경우
					else dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
				}
			}
		}
		
		long sum = 0;
		// 길이가 n일 때의 계단수의 개수를 sum에 누적해서 저장
		for(int i = 1; i < 10; i++) {
			sum += dp[n][i];
		}
		
		System.out.print(sum % 1000000000);
	}
}