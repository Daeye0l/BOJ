package dp;

import java.io.*;
import java.util.*;

public class DP1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수의 개수 입력받기
		int[] arr = new int[n]; // 입력받은 수를 저장할 배열 생성
		int[] dp = new int[n];
		
		// n개의 수를 입력받아 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이전부터 연속해온 값에 현재값을 연속하는 것이 현재 값보다 작으면 현재값부터 다시 연속하면 된다.
		dp[0] = arr[0];
		int max = dp[0];
		for(int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
			max = Math.max(dp[i], max);
		}
		
		System.out.print(max);
	}
}