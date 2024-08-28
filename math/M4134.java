package math;

import java.io.*;

public class M4134 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			long n = Long.parseLong(br.readLine());
			
			System.out.println(findDecimal(n));
		}
	}
	
	private static long findDecimal(long n) {
		if(n == 0 || n == 1 || n == 2) { 
			return 2;
		}
		else {
			boolean decimal = true;
			// 1과 자기 자신만을 약수로 가지므로 그 사이의 수들 중 제곱근 이하만 확인
			for(int i = 2; i <= Math.sqrt(n); i++) {
				if(n % i == 0) {
					decimal = false;
					break;
				}
			}
			if(decimal) return n;
			else return findDecimal(n+1);
		}
		
	}
}