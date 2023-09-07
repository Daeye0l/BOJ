package ds;

import java.io.*;
import java.util.*;

public class DS1874 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // N 입력받기
		int[] arr = new int[n]; // 1이상 n이하의 정수를 저장할 배열 생성

		// 1이상 n이하의 정수를 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Stack<Integer> stack = new Stack<>(); // 스택 생성
		StringBuilder sb = new StringBuilder();
		int j = 0; // j는 배열의 인덱스
		// 1이상 n이하의 정수를 반복하면서
		for(int i = 1; i <= n; i++) {
			stack.push(i); // 스택에 넣음
			sb.append("+"); // +추가
			// stack의 맨 위에 있는 값이 배열의 값과 같은 동안 반복
			while(!stack.isEmpty() && stack.peek() == arr[j]) {
				stack.pop(); // 값을 뺌
				sb.append("-"); // -추가
				j++; // 배열의 인덱스 증가
			}
		}
		
		if(stack.isEmpty()) { // 모든 값이 다 pop되었다면 수열 완성
			for(int i = 0; i < sb.length(); i++) {
				System.out.println(sb.charAt(i));
			}
		}
		else System.out.print("NO");
	}
}