package math;

import java.io.*;

public class M24723 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		// 각 층마다 내려올 수 있는 방법이 2개씩 있음
		System.out.println((int)Math.pow(2, n));
	}
}