package dp;

import java.io.*;
import java.util.*;

public class DP11051 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[1001][1001]; // 이항계수 nCk를 저장할 배열
		dp[0][0] = 1; // 0C0 = 1
		
		for(int i = 1; i < 1001; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) dp[i][j] = 1; // nC0 = 1
				else if(i == j) dp[i][j] = 1; // nCn = 1
				else { // 나머지 경우는 파스칼의 삼각형을 적용
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j]) % 10007;
				}
			}
		}
		
		System.out.println(dp[n][k] % 10007); // nCk 출력
	}
}