package binarySearch;

import java.io.*;
import java.util.*;

public class BS9024 {
	static int n, k, resCnt, minDiff;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			resCnt = 0;
			minDiff = Integer.MAX_VALUE;
			arr = new int[n];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			for(int j = 0; j < n-1; j++) {
				// 하나의 정수가 arr[j]라면 다른 하나의 정수는 k-arr[j]에 가까워야 한다.
				int lowerBound = binarySearch(j+1, n-1, k-arr[j]);
				int sum = arr[j] + arr[lowerBound];
				int diff = Math.abs(k-sum); // 두 정수의 합과 k의 절댓값 차이
				// 차이가 같다면 결과값 증가
				if(diff == minDiff) {
					resCnt++;
				}
				// 더 작은 차이값이 존재한다면 재설정
				if(diff < minDiff) {
					minDiff = diff;
					resCnt = 1;
				}
				
				// lowerBound - 1의 값도 확인
				if(lowerBound-1 >= j+1) {
					sum = arr[j] + arr[lowerBound-1];
					diff = Math.abs(k-sum);
					if(diff == minDiff) {
						resCnt++;
					}
					if(diff < minDiff) {
						minDiff = diff;
						resCnt = 1;
					}
				}
			}
			
			System.out.println(resCnt);
		}
	}
	
	private static int binarySearch(int left, int right, int target) {
		while(left <= right) {
			int mid = (left + right) / 2;
			if(target < arr[mid]) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		// 타겟값이 배열 최댓값 보다 큰 경우 최댓값 위치 반환
		if(left >= arr.length) {
			return right;
		}
		return left;
	}
}