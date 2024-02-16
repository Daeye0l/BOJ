package dp;

import java.io.*;
import java.util.*;

public class DP11722 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수열의 길이
		int[] numbers = new int[n]; // 수열을 저장할 배열
		int[] dp = new int[n]; // n번째 수를 마지막으로 하는 가장 긴 감소수열의 길이를 저장할 배열
		int max = 0; // 전체 수열에서 가장 긴 감소수열의 길이
		
		// 수열의 수들을 입력 받아서 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp배열 모두 1로 초기화
		for(int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(numbers[i] < numbers[j]) { // 현재 수를 기준으로 다음 수가 감소한다면
					dp[i] = Math.max(dp[i], dp[j] + 1); // 현재 저장된 최장길이와 다음 수부터의 최장길이 + 1  중 큰 값을 저장
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max); // 감소수열의 길이 최대값 출력
	}
}