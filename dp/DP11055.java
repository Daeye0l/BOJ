package dp;

import java.io.*;
import java.util.*;

public class DP11055 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수열의 길이
		int[] numbers = new int[n]; // 수열을 저장할 배열
		int[] dp = new int[n]; // n번째 수를 마지막으로 하는 증가 수열의 합을 저장할 배열
		
		// 수열의 수들 입력 받아서 배열채워넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			numbers[i] = num;
			dp[i] = num;
		}
		
		int max = dp[0]; // 증가 수열 합의 최댓값
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) { // 현재 수를 기준으로 이전 수들을 확인
				if(numbers[j] < numbers[i]) { // 이전 수가 현재 수보다 작은 경우
					dp[i] = Math.max(dp[i], dp[j] + numbers[i]); // 원래의 dp값과 현재 수를 포함하여 합을 만든 dp값 중 큰 값을 저장
					max = Math.max(max, dp[i]);
				}
			}
		}
		
		System.out.println(max);
	}
}