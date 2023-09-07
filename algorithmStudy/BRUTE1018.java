package algorithmStudy;

import java.io.*;
import java.util.*;

public class BRUTE1018 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 좌측 상단이 흰색인 체스판과 검정색인 체스판 각각 만들기
		char[][] w = {{'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'}};

		char[][] b = {{'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'},
					   {'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W'},
					   {'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B'}};

		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// n행 m열인 보드 생성
		char[][] input = new char[n][m];
		
		// 보드판의 각 칸의 색을 입력 받아서 채우기
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				input[i][j] = s.charAt(j);
			}
		}
		
		int w_min = 0;
		for(int i = 0; i < n-7; i++) {
			for(int j = 0; j < n-7; j++) {
				for(int k = 0; k < 8; k++) {
					for(int l = 0; l < 8; l++) {
						if(w[k][l] != input[k+i][l+j]) {
							w_min++;
						}
					}
				}
			}
		}
	}
}
