package prefixSum;

import java.io.*;
import java.util.*;

public class PS3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[] stalagmite = new int[n/2]; // 석순
		int[] stalactite = new int[n/2]; // 종유석
		
		for(int i = 0; i < n/2; i++) {
			stalagmite[i] = Integer.parseInt(br.readLine());
			stalactite[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(stalagmite);
		Arrays.sort(stalactite);
		
		int min = Integer.MAX_VALUE; // 파괴해야 하는 장애물의 최소 갯수
		int cnt = 0; // 장애물을 최소로 파괴하는 구간의 갯수
		for(int i = 1; i <= h; i++) {
			// i높이에 있는 석순과 종유석 갯수의 합 
			int sum = binarySearch(0, n/2, i, stalagmite) + binarySearch(0, n/2, h-i+1, stalactite);
			if(sum == min) {
				cnt++;
				continue;
			}
			if(sum < min) {
				min = sum;
				cnt = 1;
			}
		}
		
		System.out.println(min + " " + cnt);
	}
	
	// h높이에 있는 장애물 갯수 반환
	private static int binarySearch(int start, int end, int h, int[] arr) {
		while(start < end) {
			int mid = (start + end) / 2;
			if(arr[mid] >= h) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		return arr.length - end;
	}
}