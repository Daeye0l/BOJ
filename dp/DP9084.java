package dp;

import java.io.*;
import java.util.*;

public class DP9084 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < T; t++) {
			// 동전의 가지 수
			int N = Integer.parseInt(br.readLine());
			int[] cost = new int[N+1];
			
			// 각 동전의 금액을 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int n = 1; n <= N; n++) {
				cost[n] = Integer.parseInt(st.nextToken());
			}
			
			// 만들어야 할 금액
			int M = Integer.parseInt(br.readLine());
			// 금액 m을 만들기 위한 방법의 수
			int[] dp = new int[M+1];
			// 금액 0을 만들기 위한 방법은 모든 동전을 쓰지 않는 경우 하나
			dp[0] = 1;
			for(int n = 1; n <= N; n++) {
				for(int c = cost[n]; c <= M; c++) {
					dp[c] += dp[c-cost[n]];
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		
		System.out.println(sb);
	}
}