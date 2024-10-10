package binarySearch;

import java.io.*;
import java.util.*;

public class BS12738 {
	static int n;
	static int[] numbers;
	static List<Integer> result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		result = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		// 수열의 첫 번째 원소 리스트에 삽입
		result.add(numbers[0]);
		for(int i = 1; i < n; i++) {
			int num = numbers[i];
			// 수열의 원소가 리스트의 마지막 값보다 크면 삽입
			if(num > result.get(result.size()-1)) {
				result.add(num);
			}
			// 작거나 같다면 lower bound 위치에 삽입
			else {
				result.set(binarySearch(0, result.size()-1, num), num);
			}
		}

		System.out.println(result.size());
	}
	
	// lower bound 반환
	private static int binarySearch(int left, int right, int target) {
		while(left < right) {
			int mid = (left + right) / 2;
			if(target <= result.get(mid)) {
				right = mid;
			}
			else {
				left = mid + 1;
			}
		}
		return left;
	}
}
