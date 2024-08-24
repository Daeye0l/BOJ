package math;

import java.io.*;
import java.util.*;

public class M1934 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int gcd = gcd(a, b);
			
			System.out.println(a * b / gcd); // 두 수의 곱 = 최대공약수 X 최소공배수
		}
	}
	
	// a와 b의 최대공약수 = a % b와 b의 최대공약수임을 이용(어떤 수와 0의 최대공약수는 자기 자신이기 때문)
	private static int gcd(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}