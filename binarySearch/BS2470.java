package binarySearch;

import java.io.*;
import java.util.*;

public class BS2470 {
	static int n, min;
	static int[] liquid;
	static int resLiquid1, resLiquid2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		liquid = new int[n];
		resLiquid1 = 0; resLiquid2 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			liquid[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(liquid);
		
		for(int i = 0; i < n-1; i++) {
			int lowerBound = binarySearch(i+1, n-1, -liquid[i]); // 현재 용액의 부호를 반대로 한 값의 lower bound
			// lower bound 용액과
			int absSum = Math.abs(liquid[i] + liquid[lowerBound]);
			if(absSum < min) {
				min = absSum;
				resLiquid1 = liquid[i];
				resLiquid2 = liquid[lowerBound];
			}
			// lower bound 바로 전 용액 비교
			if(lowerBound-1 >= i+1) {
				absSum = Math.abs(liquid[i] + liquid[lowerBound-1]);
				if(absSum < min) {
					min = absSum;
					resLiquid1 = liquid[i];
					resLiquid2 = liquid[lowerBound-1];
				}
			}
		}
		
		System.out.println(resLiquid1 + " " + resLiquid2);
	}
	
	private static int binarySearch(int left, int right, int target) {
		while(left <= right) {
			int mid = (left + right) / 2;
			
			if(liquid[mid] < target) {
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		// 타겟값이 용액의 최대값 보다 큰 경우 용액 중 가장 큰 값을 반환
		if(left >= liquid.length) {
			return right;
		}
		return left;
	}
}