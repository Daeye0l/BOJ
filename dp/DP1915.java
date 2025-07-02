package dp;

import java.io.*;
import java.util.*;

public class DP1915 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n+1][m+1];
		int[][] dp = new int[n+1][m+1]; // (i,j)을 우측하단 꼭짓점으로 쓰는 1로된 가장 큰 정사각형의 한 변 길이
		
		for(int i = 1; i <= n; i++) {
			String line = br.readLine();
			for(int j = 1; j <= m; j++) {
				arr[i][j] = Character.getNumericValue(line.charAt(j-1));
			}
		}
		
		int max = 0;
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				if(arr[i][j] == 1) {
					dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]))+1;
					max = Math.max(max, dp[i][j]);
				}
				else dp[i][j] = 0;
			}
		}
		
		System.out.println(max*max);
	}
}