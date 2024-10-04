package binarySearch;

import java.io.*;
import java.util.*;

public class BS2467 {
	static int n, min, resLiquid1, resLiquid2;
	static int[] liquids;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		resLiquid1 = 0; resLiquid2 = 0;
		liquids = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			liquids[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < n-1; i++) {
			int lowerBound = binarySearch(i+1, n-1, -liquids[i]);
			// 현재 용액과 반대 부호 lower bound 값
			int absSum = Math.abs(liquids[i] + liquids[lowerBound]);
			if(absSum < min) {
				min = absSum;
				resLiquid1 = liquids[i];
				resLiquid2 = liquids[lowerBound];
			}
			// 현재 용액과 반대 부호 lower bound 바로 전의 값
			if(lowerBound-1 >= i+1) { // 중복 방지
				absSum = Math.abs(liquids[i] + liquids[lowerBound-1]);
				if(absSum < min) {
					min = absSum;
					resLiquid1 = liquids[i];
					resLiquid2 = liquids[lowerBound-1];
				}
			}
		}
		
		System.out.println(resLiquid1 + " " + resLiquid2);
	}
	
	private static int binarySearch(int left, int right, int target) {
		while(left <= right) {
			int mid = (left + right) / 2;
			if(liquids[mid] < target) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		// 타겟값이 용액의 최대값 보다 큰 경우 용액 중 가장 큰 값을 반환
		if(left >= liquids.length) {
			return liquids.length - 1;
		}
		return left;
	}
}