package dp;

import java.io.*;
import java.util.*;

public class DP11054 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수열의 길이 입력받기
		int[] numbers = new int[n]; // 수열을 저장할 배열
		int[] dpLeft = new int[n];
		int[] dpRight = new int[n];
		
		// 수열 채워넣기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp배열 1로 초기화
		for(int i = 0; i < n; i++) {
			dpLeft[i] = 1;
			dpRight[i] = 1;
		}
		
		// 수열의 왼쪽에서 부터 증가수열의 길이 구하기
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(numbers[j] < numbers[i]) {
					dpLeft[i] = Math.max(dpLeft[i], dpLeft[j] + 1);
				}
			}
		}
		
		// 수열의 오른쪽에서 부터 증가수열의 길이 구하기(즉, 감소수열)
		for(int i = n-1; i >= 0; i--) {
			for(int j = n-1; j >= i; j--) {
				if(numbers[j] < numbers[i]) {
					dpRight[i] = Math.max(dpRight[i], dpRight[j]+ 1);
				}
			}
		}
		
		int max = 0; // 두 dp배열 값의 합 중 가장 큰 값을 저장
		for(int i = 0; i < n; i++) {
			max = Math.max(max, dpLeft[i] + dpRight[i]);
		}
		
		System.out.println(max-1); // 중간에 있는 값은 중복되기 때문에 -1
	}
}