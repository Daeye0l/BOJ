package math;

import java.io.*;
import java.util.*;

public class M13241 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		// 두 수의 곱 = 최대공약수 x 최소공배수
		System.out.println(a * b / GCD(a, b));
	}
	
	// 유클리드 호제법으로 최대공약수 구하기
	private static long GCD(long a, long b) {
		while(b != 0) {
			long temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}