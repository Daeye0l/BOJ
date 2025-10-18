package dp;

import java.io.*;
import java.util.*;

public class DP15486 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];
		int[] price = new int[N];
		int[] dp = new int[N+1]; // dp[n] = n일째부터 받을 수 있는 최대 금액
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int T = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			
			time[i] = T;
			price[i] = P;
		}
		
		// 역순으로 갱신
		for(int i = N-1; i >=0 ; i--) {
			// i번째 날에 있는 상담을 할수 있는 경우
			if(i+time[i]-1 < N) {
				// 상담을 하는 경우와 상담을 하지 않는 경우 중 더 큰 값을 저장
				dp[i] = Math.max(price[i] + dp[i+time[i]], dp[i+1]);
			}
			// i번째 날에 있는 상담을 할수 없는 경우
			else {
				dp[i] = dp[i+1];
			}
		}
		
		System.out.println(dp[0]);
	}
}