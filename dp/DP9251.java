package dp;

import java.io.*;

public class DP9251 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 두 문자열 입력받기
		String n = br.readLine();
		String m = br.readLine();
		
		/* n과 m으로 앞에서부터 길이가 1 ~ n.length(), 1 ~ m.length()인
		   부분문자열을 만들 때 두 문자열의 LCS의 길이를 저장 */
		int[][] dp = new int[n.length() + 1][m.length() + 1];
		
		for(int i = 1; i <= n.length(); i++) {
			for(int j = 1; j <= m.length(); j++) {
				if(n.charAt(i-1) != m.charAt(j-1)) { // 두 문자열의 마지막 문자가 다른 경우
					// 두 문자열 중 하나의 문자열의 마지막 문자를 제외, 다시 두 문자열의 LCS를 비교, 두 값 중 큰 값을 저장
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				} // 두 문자열의 마지막 문자가 같은 경우
				else { // 두 문자열 모두 마지막 문자를 제외, 다시 두 문자열의 LCS를 비교
					dp[i][j] = dp[i-1][j-1] + 1;
				}
			}
		}
		
		System.out.print(dp[n.length()][m.length()]);
	}
}