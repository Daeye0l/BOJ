package implementation;

import java.io.*;

public class IMPL24267 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 코드1의 수행 횟수는 n(n-1)(n-2)/6이고 최고차항의 차수는 3이다.
		long n = Integer.parseInt(br.readLine());
		System.out.println(n*(n-1)*(n-2)/6);
		System.out.println(3);
	}
}