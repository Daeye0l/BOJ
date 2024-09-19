package prefixSum;

import java.io.*;
import java.util.*;

public class PS10986 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		long[] mod = new long[m]; // 누적합을 m으로 나눈 나머지의 개수를 저장
		long cnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long pSum = 0;
		for(int i = 0; i < n; i++) {
			pSum += arr[i];
			// 현재까지의 누적합 자체가 m으로 나누어 떨어지면 카운트를 증가하고
			if(pSum % m == 0) {
				cnt++;
			}
			// 누적합을 m으로 나눴을 때 나머지의 빈도를 계속 카운트
			mod[(int)(pSum % m)]++;
		}
		
		// 누적합을 m으로 나눈 나머지가 같은 두 지점 사이의 구간도 마찬가지로 m으로 나누어 떨어짐
		for(int i = 0; i < m; i++) {
			long tmp = mod[i];
			if(tmp != 0) {
				cnt += tmp * (tmp-1) / 2; // 조합 연산 적용
			}	
		}
		
		System.out.println(cnt);
	}
}