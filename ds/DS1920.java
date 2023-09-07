package ds;

import java.io.*;
import java.util.*;

public class DS1920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // N 입력받기
		int[] arr = new int[n];
		
		// 입력받은 N개의 수를 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr); // 배열 정렬
		
		int m = Integer.parseInt(br.readLine()); // M 입력받기
		st = new StringTokenizer(br.readLine());
		// M개의 수를 반복하면서
		for(int i = 0; i < m; i++) {
			int x = Integer.parseInt(st.nextToken());
			
			if(binary_search(x, arr)) { // 찾았다면
				System.out.println(1); // 1 출력
			}
			else { // 찾지 못했다면
				System.out.println(0); // 0 출력
			}
		}
	}
	
	// 이분탐색 메소드
	private static boolean binary_search(int x, int[] arr) {
		int f = 0; // 배열의 시작 인덱스
		int l = arr.length - 1; // 배열의 끝 인덱스 
		
		while(f <= l) { // 찾는 배열의 원소가 1개가 될 때까지
			int m = (f + l) / 2; // 배열의 중간 인덱스
			if(x > arr[m]) { // 찾으려고 하는 값이 배열의 중간 값보다 크다면
				f = m + 1; // 뒷부분에서 다시 탐색 
			}
			else if(x < arr[m]) { // 찾으려고 하는 값이 배열의 중간 값보다 작다면
				l = m - 1; // 앞부분에서 다시 탐색
			}
			else { // 찾은 경우
				return true;
			}
		}
		
		return false; // 다 탐색하고도 찾지 못한 경우
	}
}