package string;

import java.io.*;

public class S11721 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		// 문자가 10개 나올 때마다 개행
		for(int i = 1; i <= s.length(); i++) {
			System.out.print(s.charAt(i-1));
			if(i % 10 == 0) System.out.println();
		}
	}
}