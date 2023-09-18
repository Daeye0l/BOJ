package dp;

import java.io.*;
import java.util.*;

public class DP11052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 카드의 개수 입력받기
		int[] price = new int[n+1]; // 카드의 개수가 n인 카드팩의 가격을 저장
		int[] dp = new int[n+1]; // 카드 n개를 살 때 지불하는 최대금액을 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= i; j++) {
				if(i == 1) {
					dp[i] = price[i];
				}
				else {
					// i장 있는 카드팩을 사고 i-1장을 최대로 해서 지불하거나, 2장 있는 카드팩을 사고 i-2장을 최대로 지불해서 사거나... i장 있는 카드팩을 사거나
					dp[i] = Math.max(dp[i], price[j] + dp[i-j]);
				}
			}
		}
		
		System.out.print(dp[n]);
	}
}