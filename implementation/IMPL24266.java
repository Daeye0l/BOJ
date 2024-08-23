package implementation;

import java.io.*;

public class IMPL24266 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 코드1의 수행 횟수는 n^3이고 최고차항의 차수는 3이다.
		long n = Integer.parseInt(br.readLine());
		System.out.println(n*n*n);
		System.out.println(3);
	}
}