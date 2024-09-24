package prefixSum;

import java.io.*;

public class PS17425 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		long[] divisorSum = new long[1000001];
		
		// i*j의 약수 i를 누적해서 더하기
		for(int i = 1; i < 1000001; i++) {
			for(int j = 1; i * j < 1000001; j++) {
				divisorSum[i * j] += i;
			}
		}
		
		long[] prefixSum = new long[1000001];
		for(int i = 1; i < 1000001; i++) {
			prefixSum[i] = prefixSum[i-1] + divisorSum[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(prefixSum[n]).append("\n");
		}
		
		System.out.println(sb);
	}
}