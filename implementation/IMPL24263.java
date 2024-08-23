package implementation;

import java.io.*;

public class IMPL24263 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 코드 1의 수행 횟수는 n번이고 O(n)의 시간복잡도를 가지므로 최고차항의 차수는 1
		System.out.println(n);
		System.out.println(1);
	}
}