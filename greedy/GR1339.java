package greedy;

import java.io.*;
import java.util.*;

public class GR1339 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 단어의 개수 입력받기
		
		Integer[] arr = new Integer[26]; // A ~ Z의 가중치를 저장할 배열
		for(int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}
		
		for(int i = 0; i < N; i++) { // 단어의 개수만큼 반복하면서
			String word = br.readLine(); // 단어 입력받기
			
			// 각 알파벳의 가중치를 배열에 저장
			int k = 1;
			for(int j = word.length()-1; j >= 0; j--) {
				arr[word.charAt(j) - 65] += k;
				k *= 10;
			}
		}
		
		Arrays.sort(arr, Collections.reverseOrder()); // 가중치를 내림차순으로 정렬
		
		// 가중치가 높은 순으로 크기가 큰 수를 곱함
		int sum = 0;
		int k = 9;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] != 0) {
				sum += arr[i] * k; 
			}
			k--;
		}
		
		System.out.print(sum);
	}
}