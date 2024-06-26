package dp;

import java.io.*;
import java.util.*;

public class DP2565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] lines = new int[n][2];
		int[] dp = new int[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// A전봇대 번호 기준으로 정렬
		Arrays.sort(lines, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] - b[0];
			}
		});
		
		// B전봇대 번호로 가장 긴 증가하는 부분수열의 길이(겹치지 않도록 하는 전깃줄의 최대 개수) 구하기 
		int max = 0;
		for(int i = 0; i < n; i++) {
			dp[i] = 1;
		}
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(lines[i][1] > lines[j][1]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					max = Math.max(max, dp[i]);
				}
			}
		}
		
		// 전체 전깃줄 개수에서 빼면 없애야 할 전깃줄의 개수
		System.out.println(n - max);
	}
}