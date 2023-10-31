package math;

import java.io.*;
import java.util.*;

public class M4344 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 수
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken()); // 학생 수
			 
			int[] score = new int[n]; // 학생들의 점수
			int sum = 0; // 학생들 점수의 합
			for(int j = 0; j < n; j++) {
				int s = Integer.parseInt(st.nextToken());
				score[j] = s; // 점수를 저장하고
				sum += s; // 합을 누적
			}
			
			int mean = sum / n;
			
			int cnt = 0; // 평균을 넘는 학생의 수
			for(int j : score) {
				if(j > mean) cnt++;
			}
			
			System.out.println(String.format("%.3f", cnt / (float)n * 100) + "%");
		}
	}
}