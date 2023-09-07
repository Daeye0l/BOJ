package algorithmStudy;

import java.io.*;
import java.util.*;

public class Sort25305 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 응시자 수
		int k = Integer.parseInt(st.nextToken()); // 상을 받는 사람 수
		
		int[] score = new int[n]; // 점수를 저장할 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			score[i] = Integer.parseInt(st.nextToken()); // 각 학생의 점수를 배열에 저장
		}
		
		Arrays.sort(score); // 배열 정렬
		
		System.out.print(score[n-k]); // 오름차순 정렬이기 때문에 n-k번째 점수 출력
	}
}