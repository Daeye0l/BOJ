package prefixSum;

import java.io.*;
import java.util.*;

public class PS13900 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		long[] pSum = new long[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= n; i++) {
			pSum[i] = arr[i] + pSum[i-1];
		}
		
		long result = 0;
		for(int i = 1; i < n; i++) {
			result += arr[i] * (pSum[n]- pSum[i]);
		}
		
		System.out.println(result);
	}
}