package math;

import java.io.*;

public class M13909 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 창문이 열려있기 위해서는 약수의 개수가 홀수여야 함, 즉 제곱수의 개수
		System.out.println((int)Math.sqrt(n));
	}
}