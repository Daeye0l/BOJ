package dp;

import java.io.*;
import java.util.*;

public class DP2629 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 추의 갯수와 무게를 입력 받아서 저장
		int n = Integer.parseInt(br.readLine());
		int[] weight = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean[][] dp = new boolean[n+1][40001]; // dp[i][j] = i번째 추까지 사용해서 j 무게의 구슬을 확인할 수 있는지 여부
		dp[0][0] = true; // 추를 사용하지 않아도 무게 0은 확인 가능
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= 40000; j++) {
				if(dp[i][j]) { // 이전까지의 추들을 사용해서 j무게의 무게를 확인할 수 있었던 경우라면
					dp[i+1][j] = true; // 추를 사용하지 않고도 무게를 만들 수 있음
					if(j + weight[i] <= 40000) {
						dp[i+1][j+weight[i]] = true; // 추를 저울의 오른쪽에 올린 무게도 확인할 수 있음
						dp[i+1][Math.abs(j-weight[i])] = true; // 추를 양쪽에 하나씩 올리고도 확인할 수 있음
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < t; i++) {
			if(dp[n][Integer.parseInt(st.nextToken())]) { // n개의 추를 다 사용해서 무게를 만들 수 있는지 여부
				sb.append("Y");	
			}
			else sb.append("N");
			sb.append(" ");
		}
		System.out.println(sb);
	}
}