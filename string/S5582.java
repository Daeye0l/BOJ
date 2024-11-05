package string;

import java.io.*;

public class S5582 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		int max = 0; // 최장 부분 문자열의 길이를 저장
		
		int[][] dp = new int[a.length()+1][b.length()+1];
		// 두 문자열의 문자를 하나씩 비교하면서
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				// 현재 위치에서 두 문자가 같다면
				if(a.charAt(i-1) == b.charAt(j-1)) {
					// 이전 위치까지에서의 결고에 1을 더함
					dp[i][j] = dp[i-1][j-1] + 1;
				}
				// 두 문자가 다르다면 리셋
				else {
					dp[i][j] = 0;
				}
				max = Math.max(max, dp[i][j]);
			}
		}
		
		System.out.println(max);
	}
}