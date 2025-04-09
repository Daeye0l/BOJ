package dp;

import java.io.*;
import java.util.*;

public class DP11066 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		while(t --> 0) {
			int K = Integer.parseInt(br.readLine()); // 소설을 구성하는 장의 수
			int[] fileSize = new int[K+1]; // 각 장을 수록한 파일의 크기를 저장
			int[] prefixSum = new int[K+1]; // k번째 장까지의 누적합을 저장
			int[][] dp = new int[K+1][K+1]; // dp[i][j] = i~j장까지 합치는데 필요한 최소 비용
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= K; i++) {
				fileSize[i] = Integer.parseInt(st.nextToken());
				prefixSum[i] = prefixSum[i-1] + fileSize[i];
			}
			
			for(int range = 2; range <= K; range++) { // 합칠 구간의 길이
				for(int i = 1; i <= K-range+1; i++) { // 시작 위치
					int j = i+range-1; // 시작 위치와 구간의 길이를 정해졌으므로 끝 위치를 구할 수 있음
					dp[i][j] = Integer.MAX_VALUE;
					for(int k = i; k+1 <= j; k++) { // k를 기준으로 두 부분으로 나눠 구하기
						int cost = dp[i][k] + dp[k+1][j] + prefixSum[j] - prefixSum[i-1];
						dp[i][j] = Math.min(dp[i][j], cost);
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
	}
}