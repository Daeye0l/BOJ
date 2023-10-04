package string;

import java.io.*;
import java.math.*;

public class S15829 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine()); // 문자열 길이 입력받기
		
		String s = br.readLine(); // 문자열 입력받기
		
		BigInteger result = new BigInteger("0"); // 해시 값이 매우 커질 수 있기 때문에 BigInteger 사용
		for(int i = 0 ; i < L; i++) {
			// result에 해시값 저장
			result = result.add(BigInteger.valueOf(s.charAt(i)-96).multiply(BigInteger.valueOf(31).pow(i)));
		}
		// 1234567891로 나눈 나머지 출력
		System.out.println(result.remainder(BigInteger.valueOf(1234567891)));
	}
}