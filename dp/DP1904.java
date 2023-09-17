package dp;

import java.io.*;

public class DP1904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[2][n+1]; // 길이가 n이고 마지막 수가 0, 1일 때 만들 수 있는 2진 수열의 개수를 저장
		
		for(int i = 1; i <= n; i++) {
			if(i == 1) { // 길이가 1인 경우
				dp[0][i] = 0; // 0은 쌍으로 이루어졌기 2진 수열은 없음
				dp[1][i] = 1; // 1 하나가 있음
			}
			else if(i == 2){ // 길이가 2인 경우
				dp[0][i] = 1; // 00으로 하나가 있음
				dp[1][i] = 1; // 01은 불가능 하기 때문에 11로 하나가 있음
			}
			else {
				// i번째 수가 0으로 끝나는 경우 i-1번째 수는 0이므로 길이가 i-2이고 마지막 수가 0 or 1인 경우의 수를 더함
				dp[0][i] = (dp[0][i-2] + dp[1][i-2]) % 15746;
				// i번째 수가 1로 끝나는 경우 i-1번째 수는 0 or 1이므로 길이가 i-1이고 마지막 수가 0 or 1인 경우의 수를 더함
				dp[1][i] = (dp[0][i-1] + dp[1][i-1]) % 15746;
			}
		}
		
		System.out.print((dp[0][n] + dp[1][n]) % 15746);
	}
}