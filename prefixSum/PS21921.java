package prefixSum;

import java.io.*;
import java.util.*;

public class PS21921 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		long[] pSum = new long[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			pSum[i] = pSum[i-1] + Long.parseLong(st.nextToken());
		}
		
		long max = 0;
		int cnt = 0;
		for(int i = x; i <= n; i++) {
			if(pSum[i] - pSum[i-x] == max) {
				cnt++;
				continue;
			}
			if(pSum[i] - pSum[i-x] > max) {
				max = pSum[i] - pSum[i-x];
				cnt = 1;
			}
			max = Math.max(max, pSum[i] - pSum[i-x]);
		}
		
		if(max != 0) {
			System.out.println(max);
			System.out.println(cnt);
		}
		else System.out.println("SAD");
	}
}