package binarySearch;

import java.io.*;
import java.util.*;

public class BS3079 {
	static long n, m;
	static long[] times;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		times = new long[(int)n];
		
		long max = 0;
		for(int i = 0; i < n; i++) {
			times[i] = Long.parseLong(br.readLine());
			max = Math.max(times[i], max);
		}
		
		// 심사에 걸리는 시간의 최솟값은 입국 심사대의 갯수 >= 사람 수 일 때, 모든 심사대의 심사 시간이 1초인 경우
		// 심사에 걸리는 시간의 최댓값은 심사가 가장 오래 걸리는 심사대로 모든 사람이 가는 경우
		System.out.println(binarySearch(1, m * max));
	}
	
	// 심사에 필요한 최소 시간에 대하여 이분 탐색
	private static long binarySearch(long left, long right) {
		while(left < right) {
			long mid = (left + right) / 2;
			long cnt = 0;
			
			for(int i = 0; i < n; i++) {
				// 각 심사대가 mid시간에 대해 심사할 수 있는 사람의 수를 누적
				cnt += mid / times[i];
				if(cnt > m) { // m명을 초과하는 경우 종료
					break;
				}
			}
			
			if(cnt < m) { // mid시간에 대해 m명을 심사할 수 없는 경우
				left = mid + 1; // 더 큰 시간에 대해서 탐색
			}
			else {
				right = mid;
			}
		}
		return right;
	}
}