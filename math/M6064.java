package math;

import java.io.*;
import java.util.*;

public class M6064 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int lcm = (m*n) / gcd(m, n); // <x:y>를 만족하는 k의 최댓값은 m과 n의 최소공배수임
			
			int result = -1;
			// <x:y>에서 x값은 고정하고 y값만 확인(x값은 m번 주기로 돌아옴)
			for(int k = x; k <= lcm; k += m) {
				// k번째 해에 대해 y도 조건을 만족하는 경우 종료
				// 조건은 k % m == x && k % n == y
				if(k % n == y) {
					result = k+1;
					break;
				}
				
			}
			
			sb.append(result).append("\n");
		}

		System.out.println(sb);
	}
	
	// 최대공약수를 반환
	private static int gcd(int m, int n) {
		if(m % n == 0) return n;
		return gcd(n, m % n);
	}
}