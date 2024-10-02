package binarySearch;

import java.io.*;

public class BS1300 {
	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		/* 2차원 배열 A에 들어있는 수를 B에 넣고 정렬했을 때, B[k]는 이미 정렬된 상태이기 때문에
		   B[k] = x라는 것은 x보다 작거나 같은 수가 최소 k개 있다는 말이 된다. */
		
		// 임의의 수 x를 찾긴 해야 하지만 k의 최대값이 n^2이므로 x도
		System.out.println(binarySearch(1, k));
	}
	
	// x에 대하여 이진탐색
	private static long binarySearch(long start, long end) {
		while(start < end) {
			long mid = (start + end) / 2;
			long cnt = 0;
			// 2차원 배열 A에서 x보다 작거나 같은 수의 갯수를 누적(구구단과 같은 원리)
			for(int i = 1; i <= n; i++) {
				cnt += Math.min(mid/i, n); // 하지만 각 단에서 x보다 작거나 같은 원소는 최대 n개임
			}
			// x보다 작거나 같은 수의 갯수가 k 이상이라면 x를 더 작은 범위에서 탐색
			if(k <= cnt) {
				end = mid;
			}
			else {
				start = mid+1;
			}
		}
		return start;
	}
}