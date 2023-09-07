package dp;

import java.io.*;

public class DP9095 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int c = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력받기

		for(int i = 0; i < c; i++) {
			int n = Integer.parseInt(br.readLine()); // n 입력받기
			int[] arr = new int[11]; // n을 1, 2, 3의 합으로 나타내는 방법의 수를 저장할 배열
			// 우선 1, 2, 3 세가지 수를 합으로 나타내는 방법을 초기화
			arr[1] = 1; // 1
			arr[2] = 2; // 1+1, 2
			arr[3] = 4; // 1+1+1, 1+2, 2+1, 3
			for(int j = 4; j <= n; j++) {
				arr[j] = arr[j-1] + arr[j-2] + arr[j-3];
				/* 4의 경우 -> 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 1+3, 3+1
				 	1+1+1+1은 arr[3]의 1+1+1의 경우의 수와 같음
					4를 만들기 위해 1+1+1은 1을 더하는 1가지 경우 말고는 다른 경우의 수가 없기 때문
					같은 방법으로 1+1+2 ~ 3+1 모두 arr[1] ~ arr[3]에 포함된 경우이다. */

			}
			System.out.println(arr[n]);
		}
	}
}