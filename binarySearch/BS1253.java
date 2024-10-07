package binarySearch;

import java.io.*;
import java.util.*;

public class BS1253 {
	static int n, goodCnt;
	static int[] numbers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		goodCnt = 0;
		numbers = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(numbers);
		
		for(int i = 0; i < n; i++) {
			int target = numbers[i]; // 확인 하려는 수
			
			int left = 0;
			int right = n-1;
			while(left < right) {
				// 자기 자신인 경우 건너뛰기
				if(left == i) {
					left++;
					continue;
				}
				if(right == i) {
					right--;
					continue;
				}
				// 두 수의 합 구하기
				long sum = numbers[left] + numbers[right];
				// 좋은 수인 경우
				if(sum == target) {
					goodCnt++; // 카운트 증가
					break;
				}
				else if(sum < target) {
					left++;
				}
				else {
					right--;
				}
			}
		}
		
		System.out.println(goodCnt);	
	}
}
