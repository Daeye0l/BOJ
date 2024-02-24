package dp;

import java.io.*;
import java.util.*;

public class DP2294 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 동전의 개수
		int k = Integer.parseInt(st.nextToken()); // 만들어야 하는 가치
		Integer[] coins = new Integer[n]; // 동전을 저장할 배열
		int[] dp = new int[k+1]; // k원을 만들 때 필요한 동전의 최소갯수
		
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine()); // 동전을 입력받아 저장
		}
		
		// dp배열값 초기화
		for(int i = 0; i < k+1; i++) {
			if(i == 0) dp[i] = 0;
			else {
				dp[i] = 10001;	
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = coins[i]; j < k+1; j++) {
				dp[j] = Math.min(dp[j], dp[j-coins[i]]+1); // 현재 값과 i번째 동전을 포함했을 때의 값을 비교해서 최소를 저장
			}
		}
		
		System.out.println(dp[k] == 10001 ? -1 : dp[k]);
	}
}