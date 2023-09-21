package binarySearch;

import java.io.*;
import java.util.*;

public class BS2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 나무의 수
		int m = Integer.parseInt(st.nextToken()); // 집으로 가져가려고 하는 나무의 길이
		
		int[] height = new int[n]; // 나무의 높이를 저장
		int max = 0; // 가장 높은 나무의 길이
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, height[i]);
		}
		
		int f = 0;
		int l = max;
		while(f <= l) {
			int mid = (f + l) / 2; // 현재 절단기의 위치
			long sum = 0;
			
			for(int x : height) {
				if(x > mid) { // 나무의 높이가 절단기 위치보다 높은 경우
					sum += x - mid; // 절단기 위치보다 위의 길이를 가져간다.
				}
			}
			if(sum >= m) { // 목표했던 나무의 길이보다 큰 경우
				f = mid + 1; // 현재 절단기 위치보다 1 높은 위치에서 다시 탐색
			}
			else if(sum < m) { // 목표했던 나무의 길이보다 작은 경우
				l = mid - 1; // 현재 절단기 위치보다 1 낮은 위치에서 다시 탐색
			}
		}
		System.out.print(l);
	}
}