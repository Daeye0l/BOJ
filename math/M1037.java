package math;

import java.io.*;
import java.util.*;

public class M1037 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] divisor = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			divisor[i] = Integer.parseInt(st.nextToken());
		}
		
		// 서로 쌍을 이루는 약수의 성질을 이용
		int min = divisor[0];
		for(int i = 1; i < n; i++) {
			min = Math.min(min, divisor[i]);
		}
		int max = divisor[0];
		for(int i = 1; i < n; i++) {
			max = Math.max(max,  divisor[i]);
		}
		
		// 1과 n을 제외한 가장 작은 약수와 가장 큰 약수는 쌍을 이룸
		System.out.println(min * max);
	}
}