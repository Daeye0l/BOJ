package dp;

import java.io.*;

public class DP11727 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // n 입력받기
		
		int[]dp = new int[1001]; // 2xn을 채우는 방법의 수를 저장할 자료구조
		
		// n일 때의 타일을 채우는 방법은 n-1일 때 타일을 채우는 방법과 n-2일 때 타일을 채우는 방법 * 2로 이루어져 있음
		for(int i = 0; i < 1001; i++) {
			if(i == 0 || i == 1) dp[i] = 1;
			else {
				dp[i] = (dp[i-1] + 2 * dp[i-2]) % 10007;
			}
		}
		
		System.out.print(dp[n]);
	}
}