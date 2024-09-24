package prefixSum;

import java.io.*;

public class PS2851 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] pSum = new int[11];
		
		for(int i = 1; i <= 10; i++) {
			pSum[i] = pSum[i-1] + Integer.parseInt(br.readLine());
		}
		
		int minIdx = 0;
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= 10; i++) {
			int diff = Math.abs(pSum[i] - 100);
			if(diff <= min) {
				min = diff;
				minIdx = i;
			}
		}
		
		System.out.println(pSum[minIdx]);
	}
}