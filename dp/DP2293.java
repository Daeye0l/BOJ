package dp;

import java.io.*;
import java.util.*;

public class DP2293 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n과 k 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[k+1]; // 가치의 합이 k가 되게 하는 경우의 수를 저장
		int[] coin = new int[n]; // 각 동전의 가치를 저장
		
		// 각 동전의 가치 입력받기
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = 1;
		for(int i = 0; i < n; i++) {
			for(int j = coin[i]; j < k + 1; j++) {
				/* 각 동전의 종류를 돌면서 해당 동전을 최소 한 번 사용한 후
				 가치의 합을 만들 수 있는 경우의 수를 누적해서 저장 */
				dp[j] = dp[j] + dp[j - coin[i]];
			}
		}
		
		System.out.println(dp[k]);
	}
}