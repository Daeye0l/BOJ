package prefixSum;

import java.io.*;
import java.util.*;

public class PS11659 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long[] prefixSum = new long[n+1];
		
		// prefixSum[i] = 1 ~ i번째 수까지의 누적합
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken()); 
			System.out.println(prefixSum[b] - prefixSum[a-1]);
		}
	}
}