package dp;

import java.io.*;
import java.util.*;

public class DP1699 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1]; // n을 제곱수들의 합으로 표현할 때 제곱수의 최소개수를 저장
		
		// 배열 값 초기화(자연수 n을 제곱수의 합으로 표현할 때 최대 개수는 1^2으로만 더한 형태)
		for(int i = 1; i < n+1; i++) {
			dp[i] = i;
		}
		
		for(int i = 2; i < n+1; i++) { // 자연수 n에 대하여
			for(int j = 1; j*j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i - j*j] + 1); // n보다 작은 제곱수를 뺀 값의 dp값 + 1 중 가장 작은 값을 선택
			}
		}
		
		System.out.println(dp[n]);
	}
}