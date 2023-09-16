package dp;

import java.io.*;
import java.util.*;

public class DP14501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // n 입력받기
		int[] T = new int[n+1]; // 상담에 걸리는 시간을 저장할 배열
		int[] P = new int[n+1]; // 상담을 마치고 받는 금액을 저장할 배열
		int[] dp = new int[n+2]; // 처음 dp[n]을 채울 때 범위가 초과되므로 크기를 하나 추가함.
		
		// 상담 시간과 페이를 입력받아 배열에 저장
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		// n일부터 탑다운 방식으로 dp[i] 구하기, dp[i]: i일부터 상담했을 때 받는 최대 금액
		for(int i = n; i > 0; i--) { 
			if(i + T[i] > n + 1) { // i일에 상담을 할 수 없는 경우
				dp[i] = dp[i + 1]; // i+1일부터 상담했을 때 받는 최대 금액으로 채움  
			}
			else { // i일에 일을 할 수 있는 경우
				dp[i] = Math.max(P[i] + dp[i + T[i]], dp[i + 1]);
				// (i일에 상담을 해서 P[i]를 받고) + (i + T[i]일부터 상담했을 때 받는 최대 금액) vs i일에 일을 하지 않은 dp[i + 1]  
			}
		}
		
		System.out.print(dp[1]);
	}
}