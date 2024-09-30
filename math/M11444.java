package math;

import java.io.*;
import java.util.*;

public class M11444 {
	static int mod = 1000000007;
	static Map<Long, Long> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long n = Long.parseLong(br.readLine());
		map = new HashMap<>();
		map.put(0L, 0L);
		map.put(1L, 1L);
		map.put(2L, 1L);
		
		System.out.println(fib(n));
	}
	
	private static long fib(long n) {
		// 맵에 피보나치 수가 저장되어 있다면 사용
		if(map.containsKey(n)) return map.get(n);
		// 점화식을 전개하면 두 피보나치 수의 계수들 또한 피보나치를 따르고 있는 것을 알 수 있다.
		// n이 홀수인 경우와 짝수인 경우 분할이 조금 다름
		long ret = 0;
		if(n % 2 == 0) {
			ret = fib(n/2) * (fib(n/2+1)+fib(n/2-1)) % mod;
		}
		else {
			ret = (fib((n-1)/2)*fib((n-1)/2) + fib((n+1)/2)*fib((n+1)/2)) % mod;
		}
		// 중복계산을 막기 위해 저장
		map.put(n, ret);
		return ret;
	}
}