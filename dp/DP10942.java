package dp;

import java.io.*;
import java.util.*;

public class DP10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		boolean[][] dp = new boolean[n+1][n+1]; // s번째 수부터 e번째 수까지 팰린드롬인지 여부 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// s와 e가 같은 경우(숫자 한개)
		for(int i = 1; i <= n; i++) {
			dp[i][i] = true;
		}
		// 부분 수열의 길이가 2 이상인 경우(2부터 n까지 바텀업으로 확인)
		for(int len = 2; len <= n; len++) {
			for(int s = 1; s <= n-len+1; s++) {
				int e = s+len-1;
				// 양 끝의 수가 같으면 사이에 있는 부분이 팰린드롬일 경우 가능
				if(arr[s] == arr[e]) {
					if(len==2 || dp[s+1][e-1]) {
						dp[s][e] = true;
					}
				}
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(dp[s][e]) result.append(1);
			else result.append(0);
			result.append("\n");
		}
		
		System.out.println(result);
	}
}