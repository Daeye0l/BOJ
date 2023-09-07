package dp;

import java.io.*;
import java.util.*;

public class DP1932 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 삼각형의 크기 입력받기
		int[][] arr = new int[n+1][n+1]; // 삼각형 각 층의 수들을 저장할 배열 생성
		int[][] dp = new int[n+1][n+1]; // 삼각형의 각 수까지 오는데의 최대값을 저장할 배열 생성
		
		// 수들을 입력받아 배열에 저장
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 수까지 오는데의 최댓값을 저장
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				dp[i][j] = arr[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
			}
		}
		
		// 마지막 줄에 있는 수까지 오는데의 최댓값 중 가장 큰 값을 max에 저장
		int max = 0;
		for(int i = 0; i <= n; i++) {
			max = Math.max(dp[n][i], max);
		}
		
		System.out.print(max);
	}
}