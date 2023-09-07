package dp;

import java.io.*;

public class DP1463 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 정수 N 입력받기
		int[] arr = new int[n+1]; // N을 1로 만들기 위한 최소 연산횟수를 저장할 배열
		
		arr[0] = 0; arr[1] = 0; // 입력받은 수가 0이거나 1이면 연산횟수는 0번
		for(int i = 2; i <= n; i++) {
			if(i % 6 == 0) { // N이 2, 3으로 나눠지는 경우
				// 3가지 연산이 모두 가능하다.
				arr[i] = Math.min(Math.min(arr[i/2], arr[i/3]), arr[i-1]) + 1;
			}
			else if(i % 3 == 0) { // N이 3으로 나눠지는 경우
				// N을 3으로 나누는 연산과 N에서 1을 빼는 연산이 가능하다.
				arr[i] = Math.min(arr[i/3], arr[i-1]) + 1;
			}
			else if(i % 2 == 0) { // N이 2로 나눠지는 경우
				// N을 2로 나누는 연산과 N에서 1을 빼는 연산 중 연산횟수가 작은 것을 선택
				arr[i] = Math.min(arr[i/2], arr[i-1]) + 1;
			}
			else { // N이 2또는 3으로 나눠지지 않는 경우
				// 1을 빼는 연산만 가능
				arr[i] = arr[i-1] + 1;
			}
		}
		
		System.out.print(arr[n]);
	}
}