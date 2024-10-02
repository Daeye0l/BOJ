package binarySearch;

import java.io.*;
import java.util.*;

public class BS2512 {
	static int n, m;
	static int[] request;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		request = new int[n];
		int sum = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			request[i] = Integer.parseInt(st.nextToken());
			sum += request[i];
		}
		Arrays.sort(request);
		
		m = Integer.parseInt(br.readLine());
		
		// 요청 예산의 합이 총 예산 이하인 경우(모두 요청한대로 나눠줄 수 있으므로 요청한 금액 중 가장 큰 값)
		if(sum <= m) System.out.println(request[request.length-1]);
		else System.out.println(binarySearch(0, request[request.length-1]));
	}
	
	// 상한액 탐색
	private static int binarySearch(int left, int right) {
		while(left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			// 상한액 이하인 요청들은 요청 금액을 더하고 초과인 요청들은 상한액을 더함
			for(int i = 0; i < n; i++) {
				if(request[i] <= mid) sum += request[i];
				else sum += mid;
			}
			// 완성된 예산이 총 예산을 넘는 경우
			if(sum > m) {
				right = mid - 1; // 상한액을 더 작은 범위에서 탐색
			}
			// 완성된 예산이 총 예산 이하인 경우
			else {
				left = mid + 1;
			}
		}
		return right;
	}
}