package algorithmStudy;

import java.io.*;
import java.util.*;

public class ARR2563 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 색종이의 수
		int[][] arr = new int[101][101]; // 가로 세로 크기가 각각 100인 도화지
		int area = 0; // 색종이가 붙은 영역
		
		for(int i = 0; i < n; i++) {
			// 색종이의 왼쪽 아래 좌표인 x, y 입력받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 도화지의 아직 비어있는 구역에 색종이 붙이기
			for(int k = y; k < y + 10; k++) {
				for(int j = x; j < x + 10; j++) {
					if(arr[j][k] == 0) arr[j][k] = 1;
				}
			}
		}
		
		// 색종이가 붙은 영역의 넓이 구하기
		for(int i = 0; i < 101; i++) {
			for(int j = 0; j < 101; j++) {
				if(arr[i][j] == 1) area++;
			}
		}
		
		System.out.print(area);
	}
}