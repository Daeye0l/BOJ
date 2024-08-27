package math;

import java.io.*;

public class M2485 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] tree = new int[n];
		int[] distance = new int[n-1]; // 가로수 사이 거리 저장
		
		for(int i = 0; i < n; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1; i < n; i++) {
			distance[i-1] = tree[i] - tree[i-1];
		}
		
		// 가로수 사이 거리들의 최대공약수 구하기
		int gcd = GCD(distance[1], distance[0]);
		for(int i = 2; i < n-1; i++) {
			gcd = GCD(gcd, distance[i]);
		}
		
		// 가로수 사이에 최대공약수 만큼 떨어진 거리마다 심기
		int cnt = 0;
		for(int i = 1; i < n; i++) {
			int j = tree[i];
			while(j - gcd != tree[i-1]) {
				cnt++;
				j -= gcd;
			}
		}
		
		System.out.println(cnt);
	}
	
	private static int GCD(int a, int b) {
		while(b != 0) {
			int temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}