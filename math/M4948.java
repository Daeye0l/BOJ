package math;

import java.io.*;

public class M4948 {
	static boolean[] check = new boolean[246913];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		seive(); // 에라토스테네스의 체 연산 실행
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) {
				return;
			}
			
			int cnt = 0; // 범위에서 check되지 않은 소수의 개수 세기
			for(int i = n+1; i <= 2*n; i++) {
				if(!check[i]) cnt++;
			}
			
			System.out.println(cnt);
		}
	}
	
	private static void seive() {
		check[0] = true;
		check[1] = true;
		for(int i = 2; i < check.length; i++) {
			if(check[i] == true) {
				continue;
			}
			// 어떤 수의 배수는 소수가 될 수 없음
			for(int j = 2 * i; j < check.length; j += i) {
				check[j] = true;
			}
		}
	}
}