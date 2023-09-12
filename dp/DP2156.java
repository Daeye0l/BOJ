package dp;

import java.io.*;

public class DP2156 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 포도주 잔의 개수 입력받기
		int[] arr = new int[n+1];
		int[] dp = new int[n+1]; // n번째 잔까지 포도주를 마실 때 최대 양을 저장할 자료구조
		
		// 각 잔에 들어있는 포도주의 양을 입력받아 배열에 저장
		for(int i = 1 ; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i = 1; i <= n; i++) {
			if(i == 1) dp[1] = arr[1];
			else if(i == 2) dp[2] = arr[1] + arr[2]; // 포도주의 양은 음이 될 수 없기 때문에 arr[1] + arr[2]가 arr[2]보다는 무조건 큼
			else {
				// n번 잔까지 포도주를 마실 때의 최대 양은 n번 잔을 마시는 경우와 마시지 않는 경우 2가지가 있음.
				dp[i] = Math.max(Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]), dp[i-1]);
			}	
		}
		System.out.print(dp[n]);
	}
}