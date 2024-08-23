package implementation;

import java.io.*;

public class IMPL24265 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Integer.parseInt(br.readLine());
		
		// 코드1의 수행 횟수는 (n-1) + (n-2) + ... + 1이므로 n(n-1)/2이고 최고차항의 차수는 2이다.
		System.out.println(n*(n-1)/2);
		System.out.println(2);
	}
}