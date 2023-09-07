package greedy;

import java.io.*;
import java.util.*;

public class GR1026 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // N 입력받기
		int[] a = new int[n]; // 배열 A 생성
		Integer[] b = new Integer[n]; // 배열 B 생성
		
		// 정수를 입력 받아 A배열과 B배열 채우기
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a.length; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b.length; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		// A배열의 가장 작은 값과 B배열의 가장 큰 값을 연결해야 합이 최소가 된다.
		Arrays.sort(a);
		Arrays.sort(b, Collections.reverseOrder());
		
		int sum = 0;
		for(int i = 0; i < n; i++) {
			sum += a[i] * b[i];
		}
		
		System.out.print(sum);
	}
}