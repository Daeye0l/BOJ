package math;

import java.io.*;
import java.util.*;

public class M1735 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a1 = Integer.parseInt(st.nextToken());
		int b1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int a2 = Integer.parseInt(st.nextToken());
		int b2 = Integer.parseInt(st.nextToken());
		
		long lcm = b1 * b2 / GCD(b1, b2); // 두 분모의 최소공배수
		long a3 = (a1*lcm/b1)+(a2*lcm/b2); // 통분한 분자값
		long b3 = lcm; // 통분한 분모값
		
	    // 분모와 분자를 최대공약수로 기약분수로 만듦
		System.out.println(a3 / GCD(a3, b3) + " " + b3 / GCD(a3, b3));
	}
	
	private static long GCD(long b1, long b2) {
		while(b2 != 0) {
			long temp = b2;
			b2 = b1 % b2;
			b1 = temp;
		}
		return b1;
	}
}