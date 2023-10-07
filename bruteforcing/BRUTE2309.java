package bruteforcing;

import java.io.*;
import java.util.*;

public class BRUTE2309 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 아홉 난쟁이의 키를 입력받아 배열에 저장
		int[] arr = new int[9]; 
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr); // 배열 정렬
		
		// 아홉 난쟁이의 키의 합을 sum에 저장
		int sum = 0;
		for(int i = 0; i < 9; i++) {
			sum += arr[i];
		}
		
		// 아홉 난쟁이의 키를 다 더한 값에서 2명을 뺐을 때 100이 된다면 해당 2명이 가짜
		boolean find = false;
		for(int i = 0; i < 8; i++) {
			if(find) break;
			for(int j = i+1; j < 9; j++) {
				if(sum - arr[i] - arr[j] == 100) {
					// 가짜 난쟁이 2명의 키를 0으로 바꾸고
					arr[i] = 0;
					arr[j] = 0;
					find = true;
					break;
				}
			}
		}
		
		// 0이 아닌 값을 출력
		for(int i = 0; i < 9; i++) {
			if(arr[i] != 0) {
				System.out.println(arr[i]);
			}
		}
	}
}