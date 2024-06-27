package dp;

import java.io.*;

public class DP9529 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		for(int i = 0; i < s1.length()+1; i++) {
			for(int j = 0; j < s2.length()+1; j++) {
				if(i == 0 || j == 0) dp[i][j] = 0;
				else {
					if(s1.charAt(i-1) == s2.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1] + 1;
					}
					else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
		}
		
		// LCS 문자열을 역추적 해서 구하기
		StringBuilder sb = new StringBuilder();
		int x = s1.length();
		int y = s2.length();
		while(x != 0 && y != 0) {
			// 두 문자가 같은 경우 StringBuilder에 추가하고 두 인덱스 모두 -1
			if(s1.charAt(x-1) == s2.charAt(y-1)) {
				sb.append(s1.charAt(x-1));
				x--; y--;
			}
			// 두 문자가 같지 않다면 LCS의 값이 큰 쪽으로 이동
			else if(dp[x-1][y] >= dp[x][y-1]) x--;
			else if(dp[x][y-1] >= dp[x-1][y]) y--;
		}
		
		System.out.println(dp[s1.length()][s2.length()]);
		System.out.println(sb.reverse());
	}
}