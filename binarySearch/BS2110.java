package binarySearch;

import java.io.*;
import java.util.*;

public class BS2110 {
	static int n, c;
	static int[] coordinate;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		coordinate = new int[n];
		
		for(int i = 0; i < n; i++) {
			coordinate[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(coordinate);
		
		// 공유기 설치 거리가 될 수 있는 가장 작은 값과 가장 큰 값으로 탐색
		System.out.println(binarySearch(1, coordinate[n-1]-coordinate[0]));
	}
	
	private static int binarySearch(int low, int high) {
		while(low <= high) {
			int mid = (low + high) / 2;
			// mid 거리로 공유기 c개를 설치하지 못하는 경우
			if(emplace(mid) < c) {
				high = mid-1;
			}
			else {
				low = mid+1;
			}
		}
		return high;
	}
	
	// distance 만큼의 거리로 설치할 수 있는지 공유기의 개수 
	private static int emplace(int distance) {
		int cnt = 1;
		int prev = coordinate[0];
		for(int i = 1; i < n; i++) {
			int curr = coordinate[i];
			if(curr - prev >= distance) {
				cnt++;
				prev = curr;
			}
		}
		return cnt;
	}
}