package math;

import java.io.*;
import java.util.*;

public class M1929 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// M과 N입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] sieve = new int[N+1];
		
		// 2 ~ N까지의 수를 입력받아 체에 저장
		for(int i = 2; i <= N; i++) {
			sieve[i] = i;
		}
		
		// 2부터 반복하며 각 수의 배수를 체에서 지움
		for(int i = 2; i <= N; i++) {
			if(sieve[i] == 0) continue;
			for(int j = i+i; j <= N; j += i) {
				sieve[j] = 0;
			}
		}
		
		// 남아있는 수가 소수이다.
		for(int i = M; i <= N; i++) {
			if(sieve[i] != 0) {
				System.out.println(sieve[i]);
			}
		}
	}
}