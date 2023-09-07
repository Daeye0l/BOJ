package dp;

import java.io.*;

public class DP11726 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1]; // 2xn 을 두 가지 타일로 채우는 방법의 수를 저장할 배열

		arr[1] = 1; // 2x1을 채우는 경우의 수는 1
		if(n >= 2) arr[2] = 2; // 2x2를 채우는 경우의 수는 2
		
		// 9095번 문제와 마찬가지로 n의 경우의 수가 n-1, n-2의 경우의 수로 포함된 문제이다. 
		for(int i = 3; i <= n; i++) {
			arr[i] = (arr[i-1] + arr[i-2]) % 10007;
		}
		
		System.out.print(arr[n]);
	}
}