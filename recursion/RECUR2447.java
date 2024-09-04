package recursion;

import java.io.*;

public class RECUR2447 {
	static String[][] square;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 전부 별로 채워진 배열 생성
		int n = Integer.parseInt(br.readLine());
		square = new String[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				square[i][j] = "*";
			}
		}
		
		// 별이 채워진 배열에서 가운데 부분을 공백으로 치환 재귀 수행
		recursion(square, n, 0, 0);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				sb.append(square[i][j]);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

	private static void recursion(String[][] square, int n, int f, int r) {
		if(n == 1) return;
		int step = n / 3;
		// 공백 찍기
		for(int i = f+step; i < f+2*step; i++) {
			for(int j = r+step; j < r+2*step; j++) {
				square[i][j] = " ";
			}
		}
		// 9개 분할에 대해 재귀 수행
		for(int i = 0 ; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				recursion(square, step, f+i*step, r+j*step);
			}
		}
	}
}