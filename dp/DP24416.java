package dp;

import java.io.*;

public class DP24416 {
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] f = new int[n+1];
		
		fib(n);
		System.out.print(cnt + " ");
		cnt = 0;
		fibonacci(n, f);
		System.out.print(cnt);
		
	}
	
	private static int fib(int n) {
		if(n == 1 || n == 2) {
			cnt++;
			return 1;
		}
		else {
			return fib(n-1) + fib(n-2);
		}
	}
	
	private static int fibonacci(int n, int[] f) {
		f[1] = 1;
		f[2] = 1;
		for(int i = 3; i <= n; i++) {
			cnt++;
			f[i] = f[i-1] + f[i-2];
		}
		return f[n];
	}
 }