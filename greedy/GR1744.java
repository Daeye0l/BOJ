package greedy;

import java.io.*;
import java.util.*;

public class GR1744 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수열의 길이
		
		int[] numbers = new int[N]; // 수열을 저장할 배열
		
		for(int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(numbers); // 수열을 오름차순으로 정렬
		
		int sum = 0; // 최대합을 저장할 변수
		int i = 0; // 음수들과 0으로 처리하기 위한 인덱스
		int j = numbers.length-1; // 양수들을 처리하기 위한 인덱스
		
		while(i < N && numbers[i] < 1) { // i 인덱스가 수열 인덱스 범위에 포함되고 1보다 작은 경우(즉 0 or 음수)
			// i+1 인덱스가 수열 인덱스 범위에 포함되고 numbers[i+1] 값이 1보다 작은 경우(즉 0 or 음수)
			if(i + 1 < N && numbers[i + 1] < 1) {
				sum += numbers[i] * numbers[i + 1];
				i += 2;
			}
			// i+1 인덱스가 수열 인덱스 범위를 벗어나거나 numbers[i+1] 값이 자연수인 경우(즉 0 or 음수인 수가 i 인덱스의 수 하나만 남았을 때)
			else {
				sum += numbers[i];
				i += 1;
			}
		}
		
		while(j >= i && numbers[j] > 0) { // j 인덱스의 수가 자연수인 경우
			if(j - 1  >= i && numbers[j - 1] > 1) { // j-1 인덱스의 수가 자연수인 경우
				sum += numbers[j] * numbers[j - 1];
				j -= 2;
			}
			else { // j-1 인덱스가 i 보다 작아졌거나 numbers[j-1] 값이 0 or 음수인 경우 
				sum += numbers[j];
				j -= 1;
			}
		}
		
		System.out.println(sum);
	}
}