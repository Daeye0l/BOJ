package implementation;

import java.io.*;

public class IMPL15439 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		// 상의 하나를 고르고 나면 생상이 다른 n-1개 중 하나를 골라야 한다
		System.out.println(n*(n-1));
	}
}