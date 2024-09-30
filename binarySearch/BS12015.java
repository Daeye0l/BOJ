package binarySearch;

import java.io.*;
import java.util.*;

public class BS12015 {
	static List<Integer> lis; // 가장 긴 증가하는 부분 수열을 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		lis = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		lis.add(arr[0]); // 첫 원소 리스트에 추가
		for(int i = 1; i < n; i++) {
			// lis의 마지막 원소보다 큰 값이라면 lis에 추가
			if(arr[i] > lis.get(lis.size()-1)) {
				lis.add(arr[i]);
			}
			else {
				lis.set(binarySearch(0, lis.size(), arr[i]), arr[i]);
			}
		}
		
		System.out.println(lis.size());
	}
	
	// lower bound 이진탐색
	private static int binarySearch(int start, int end, int target) {
		while(start < end) {
			int mid = (start+end) / 2;
			if(lis.get(mid) >= target) {
				end = mid;
			}
			else {
				start = mid + 1;
			}
		}
		return end;
	}
}