package algorithmStudy;

import java.io.*;

public class ARR10798 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] arr = new char[15][15]; // 15x15 2차원 배열 생성
		
		for(int i = 0; i < 5; i++) { // 5줄의 입력을 반복
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++) {
				arr[i][j] = s.charAt(j); // 각 문자열의 문자들을 배열에 집어넣기
			}
		}
		
		// 배열에 있는 값을 세로로 출력
		for(int j = 0; j < 15; j++) { 
			for(int i = 0; i < 15; i++) {
				if(arr[i][j] == '\0') continue;
				else System.out.print(arr[i][j]);
			}
		}
	}
}