package math;

import java.io.*;
import java.util.*;

public class M1629 {
	static long A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		System.out.println(fasterPow(A, B));
	}
	
	private static long fasterPow(long a, long b) {
		if(b == 1) {
			return a % C;
		}
		long tmp = fasterPow(a, b / 2) % C;
		if(b % 2 != 0) {
			return ((tmp * tmp) % C * A) % C;
		}
		else {
			return tmp * tmp % C;
		}
	}
}