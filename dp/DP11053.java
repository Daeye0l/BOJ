package dp;

import java.io.*;
import java.util.*;

public class DP11053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수열의 크기 입력받기
		int[] arr = new int[n+1]; // 입력받은 수를 저장할 배열
		int[] dp = new int[n+1]; // n번째 수를 마지막으로 하는 증가수열 길이의 최댓값
		
		// 수를 입력받아 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int max = 0; // 증가수열 길이의 최댓값을 저장할 변수
		for(int i = 1; i <= n; i++) {
			dp[i] = 1;
			for(int j = 1; j < i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			max = Math.max(dp[i], max);
		}
		
		System.out.print(max);
	}
}