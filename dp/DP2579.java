package dp;

import java.io.*;

public class DP2579 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 계단의 개수 입력받기
		int[] arr = new int[n+1]; // 각 계단의 점수를 저장할 배열 생성
		int[] dp = new int[n+1]; // 각 계단까지 오는 데 최대 점수를 저장할 배열 생성
		
		// 각 계단의 점수를 입력받아 배열에 저장
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[1] = arr[1]; // 1번 계단까지의 최대점수는 1번 계단 그 자체의 점수
		if(n >= 2) {
			dp[2] = arr[1] + arr[2]; // 2번 계단까지의 최대점수는 1번 계단 + 2번 계단의 점수
		}
		if(n >= 3) {
			// 3번 계단까지의 최대점수는 1번 계단을 밟고 3번으로 온 점수와 2번 계단을 밟고 3번으로 온 점수 중 큰 값
			dp[3] = Math.max(arr[1]+arr[3], arr[2]+arr[3]);
		}
		for(int i = 4; i <= n; i++) {
			/* n번 계단의 입장에서 n번 계단으로 오기 위해서는 n-2번 계단을 밟고 n번으로 오거나
		 	n-1번 계단을 밟고 n번으로 오는 경우 중 하나이고 여기서 n-1번을 밟고 오는 경우는
			2번 조건에 의해 n-3번 계단을 무조건 밟아야 한다.*/
			dp[i] = Math.max(dp[i-2], arr[i-1]+dp[i-3]) + arr[i];
		}
		
		System.out.print(dp[n]);
	}
}