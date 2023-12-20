package dp;

import java.io.*;
import java.util.*;

public class DP9465 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수

		for(int i = 0; i < T; i++) {
			int n = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][n]; // 2 x n의 배열 생성
			int[][] dp = new int[2][n]; // 동적 자료구조 생성 

			for(int j = 0; j < 2; j++) { // 두 줄에 대하여 반복
				StringTokenizer st = new StringTokenizer(br.readLine()); // 줄 단위로 입력받아
				for(int k = 0; k < n; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken()); // 배열에 값 저장하기
				}
			}
			
			// 첫 번째 열의 스티커를 뗐을 때의 최대 점수는 그 스티커 점수 자체
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			
			for(int j = 1; j < n; j++) {
				if(j == 1) { // 두 번째 열의 스티커를 뗐을 때의 최대 점수는 그 스티커와 바로 전 대각선 스티커의 점수의 합  
					dp[0][j] = dp[1][j-1] + arr[0][j];
					dp[1][j] = dp[0][j-1] + arr[1][j];
				}
				else {
					// 나머지 경우는 바로 전 스티커를 떼고 오거나 전전 스티커를 떼고 오는 경우 중 큰 점수
					dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
					dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
				}	
			}
			System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
		}
	}
}