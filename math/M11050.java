package math;

import java.io.*;
import java.util.*;

public class M11050 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n과 k를 입력받아 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		/*       n(n-1)(n-2)...(n-k)
		   nCk = -------------------
				   k(k-1)(k-2)...1     */
		
		// n(n-1)(n-2)...(n-k) 구하기
		int mul_n = 1;
		for(int i = n; i > n-k; i--) {
			mul_n *= i;
		}
		
		// k(k-1)(k-2)...1 구하기
		int mul_k = 1;
		for(int i = k; i >= 1; i--) {
			mul_k *= i;
		}
		
		System.out.print(mul_n / mul_k);
	}
}