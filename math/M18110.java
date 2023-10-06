package math;

import java.io.*;
import java.util.Arrays;

public class M18110 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 난이도 의견의 개수 입력받기
		int del = Math.round(n * ((float)3/10) / 2); // 위, 아래에서 제외해야 하는 사람의 수
		int[] arr = new int[n]; // 난이도를 저장힐 배열 생성
		
		// 난이도 입력받아 저장하기
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr); // 배열 정렬하기
		
		// 위, 아래에서 제외한 나머지 사람들이 제출한 난이도 의견의 합 구하기
		int sum = 0;
		for(int i = del; i < n-del; i++) {
			sum += arr[i];
		}
		
		// 평균 구하기
		System.out.print(Math.round((float)sum / (n-del*2)));
	}
}