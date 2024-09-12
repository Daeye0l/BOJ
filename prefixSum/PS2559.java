package prefixSum;

import java.io.*;
import java.util.*;

public class PS2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] numbers = new int[n];
		
		st = new StringTokenizer(br.readLine());
		long prefixSum = 0;
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
			// 처음 k개의 누적합 구하기
			if(i < k) prefixSum += numbers[i];
		}
		
		int f = 0;
		int r = f+k;
		long max = prefixSum; // 누적합의 최댓값을 저장
		while(r < n) {
			// 연속하는 k개의 수 중에서 가장 앞에 있는 수를 빼고 마지막 수의 다음 수를 더한 것이 다음 누적합
			prefixSum = prefixSum - numbers[f] + numbers[r];
			max = Math.max(max, prefixSum);
			f++; r++;
		}
		
		System.out.println(max);
	}
}