package implementation;

import java.io.*;

public class IMPL24264 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 코드 1의 수행 횟수는 n^2번이고 O(n^2)의 시간복잡도를 가지므로 최고차항의 차수는 2
		long n = Integer.parseInt(br.readLine());
		System.out.println(n*n);
		System.out.println(2);
	}
}