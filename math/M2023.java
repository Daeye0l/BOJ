package math;

import java.io.*;

public class M2023 {
	static int n;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		recursion(0, 0);
		System.out.println(result);
	}
	
	private static void recursion(int num, int depth) {
		// 재귀 깊이가 n자릿수를 만족하면 결과에 추가
		if(depth == n) {
			result.append(num).append("\n");
		}
		// 소수인 수는 자릿수를 추가하여 재귀
		for(int i = 0; i < 10; i++) {
			int next = num * 10 + i;
			if(isPrime(next)) {
				recursion(next, depth+1);
			}
		}
	}
	
	private static boolean isPrime(int num) {
		if(num < 2) return false; // 2보다 작은 소수는 없음
		// num을 2부터 num의 제곱근까지 나눠서 나눠지면 소수가 아님
		for(int i = 2; i <= Math.sqrt(num); i++) {
			if(num % i == 0) return false;
		}
		return true;
	}
}
