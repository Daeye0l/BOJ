package binarySearch;

import java.io.*;
import java.util.*;

public class BS2343 {
	static int n, m;
	static int[] lessons;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lessons = new int[n];
		
		// 강의의 순서가 바뀌면 안되기 때문에 정렬X
		st = new StringTokenizer(br.readLine());
		int max = 0;
		int min = 0;
		for(int i = 0; i < n; i++) {
			lessons[i] = Integer.parseInt(st.nextToken());
			// 1 <= m <= n이므로 블루레이 크기의 최솟값은 m이 n인 경우, 강의 시간 중 최댓값이고,
			// 블루레이 크기의 최댓값은 m이 1인 경우, 전체 강의 시간의 합이 된다.
			min = Math.max(lessons[i], min);
			max += lessons[i];
		}
		
		System.out.println(binarySearch(min, max));
	}
	
	// 가능한 블루레이 크기에 대해 이분탐색
	private static int binarySearch(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2; // 확인하려는 블루레이의 크기
			
			int sum = 0;
			int cnt = 0; // 블루레이의 갯수
			for(int i = 0; i < n; i++) {
				// 강의 시간의 합이 mid값 보다 작거나 같을 때까지만 누적하고 cnt 증가 
				if(sum + lessons[i] > mid) {
					cnt++;
					sum = 0;
				}
				sum += lessons[i];
			}
			if(sum != 0) {
				cnt++;
			}
			
			// 구한 블루레이의 갯수가 원하는 수 이하인 경우
			if(cnt <= m) {
				// 블루레이의 크기를 줄여서 갯수 늘리거나 최소 블루레이 크기 찾기
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		return left;
	}
}