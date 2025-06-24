package math;

import java.io.*;
import java.util.*;

public class M13458 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] room = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 각 시험장의 응시자 수 저장
		for(int i = 0; i < n; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}
		
		// 총감독관과 부감독관이 감시할 수 있는 응시자의 수 저장
		st = new StringTokenizer(br.readLine());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		long result = 0;
		for(int i = 0; i < n; i++) {
			// 총감독관이 혼자 감시할 수 있는 경우
			if(b >= room[i]) {
				result += 1;
			}
			// 총감독관이 혼자 감시할 수 없는 경우
			else {
				// 총감독관 한명과
				result += 1;
				// 부감독관이 여러명이 감시
				if((room[i]-b) % c == 0) result += (room[i]-b)/c;
				else result += (room[i]-b)/c + 1;
			}
		}
		
		System.out.println(result);
	}
}