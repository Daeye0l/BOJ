package dp;

import java.io.*;
import java.util.*;

public class DP1010 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); // 테스트케이스 개수 입력받기
		
		/* N개의 사이트에서 M개의 사이트로 다리를 잇는 경우의 수는 조합을 사용해서 M개 중에
		   N개를 뽑는 경우의 수와 같다. 조합은 순서를 고려하지 않기 때문에 다리가 겹치는 경우가
		   있더라도 상관 없음. */
		
		int[][] dp = new int[31][31]; // mCn을 저장할 자료구조 생성
		
		for(int i = 0; i < 31; i++) {
			for(int j = 0; j <= i; j++) {
				if(j == 0) dp[i][j] = 1; // mC0은 1
				else {
					// mCn = m-1Cn + m-1Cn-1
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		
		// n과 m을 입력받아 mCn을 출력
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			System.out.println(dp[m][n]);
		}
	}
}