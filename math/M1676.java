package math;

import java.io.*;
import java.math.*;

public class M1676 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // n 입력받기
		
		// n의 최대값은 500이고 500!은 엄청나게 큰 수이기 때문에 BigInteger 사용
		BigInteger fac = new BigInteger("1");
		for(int i = n; i > 0; i--) {
			fac = fac.multiply(new BigInteger(Integer.toString(i)));
		}
		
		int count = 0;
		while(true) {
			// 팩토리얼 수를 10으로 나눈 나머지 즉 1의 자리수가 k 
			BigInteger k = fac.remainder(new BigInteger("10")); 
			if(k.equals(new BigInteger("0"))) { // 1의 자리수가 0이 맞다면
				count++; // 카운트 증가
			}
			else break; 
			// 팩토리얼 수를 10으로 나눔
			fac = fac.divide(new BigInteger("10"));
		}
		
		System.out.print(count);
	}
}