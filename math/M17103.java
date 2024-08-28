package math;

import java.io.*;
import java.util.*;

public class M17103 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		boolean[] decimal = new boolean[1000001];
		Arrays.fill(decimal, true);
		seive(decimal);
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int cnt = 0;
			for(int j = 2; j <= n/2 ; j++) {
				// 순서만 바뀐 경우는 같은 쌍이므로 절반 까지만 탐색
				if(decimal[j] && decimal[n-j]) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
	
	private static void seive(boolean[] decimal) {
		decimal[0] = false;
		decimal[1] = false;
		for(int i = 2; i < decimal.length; i++) {
			if(!decimal[i]) {
				continue;
			}
			for(int j = 2*i; j< decimal.length; j += i) {
				decimal[j] = false;
			}
		}
	}
}