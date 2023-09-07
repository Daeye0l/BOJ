package algorithmStudy;

import java.io.*;
import java.util.*;

public class S2908 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		int rev_a = 0; // a의 역순을 저장할 변수
		int rev_b = 0; // b의 역순을 저장할 변수
		
		while(a != 0 && b != 0) {
			int digit_a = a % 10;
			int digit_b = b % 10;
			
			// 1의자리 수부터 맨앞으로 구성
			rev_a = rev_a * 10 + digit_a;
			a /= 10; 
			
			rev_b = rev_b * 10 + digit_b;
			b /= 10;
		}
		
		// 큰 수를 출력
		System.out.print(Math.max(rev_a, rev_b));
	}
}
