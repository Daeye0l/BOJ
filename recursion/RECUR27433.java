package recursion;

import java.io.*;

public class RECUR27433 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Integer.parseInt(br.readLine());
		
		System.out.println(factorial(n));
	}
	
	private static long factorial(long n) {
		if(n == 0 || n == 1) {
			return 1;
		}
		return n * factorial(n-1);
	}
}