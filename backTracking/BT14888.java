package backTracking;

import java.io.*;
import java.util.*;

public class BT14888 {
	static int n;
	static long max, min;
	static int[] arr;
	static int[] operator;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		max = -1000000000; min = 1000000000;
		arr = new int[n];
		operator = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, arr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	// 연산자의 개수를 depth로 하여 dfs
	private static void dfs(int depth, long temp) {
		// 연산자를 모두 사용시 결과 값을 가지고 최대값과 최소값을 갱신
		if(depth == n-1) {
			max = Math.max(max, temp);
			min = Math.min(min, temp);
		}
		
		for(int i = 0; i < 4; i++) {
			if(operator[i] != 0) { // 연산자가 있는 경우
				operator[i]--; // 연산자 사용
				dfs(depth + 1, calculation(temp, arr[depth+1], i));
				operator[i]++; // 재귀 수행이 끝나고 나면 연산자 복구 
			}
		}
	}
	
	// 계산 메소드
	private static long calculation(long a, long b, int op) {
		if(op == 0) return a + b;
		else if(op == 1) return a - b;
		else if(op == 2) return a * b;
		else return a / b;
	}
}