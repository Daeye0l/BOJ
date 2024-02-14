package dp;

import java.io.*;
import java.util.*;

public class DP11660 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] table = new int[n+1][n+1]; // 정수를 저장할 표
		int sum; // 누적합을 저장
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				table[i][j] = table[i][j-1] + Integer.parseInt(st.nextToken()); // 행별로 값을 누적하여 다음 열에 저장
			}
		}
		
		for(int i = 0; i < m; i++) {
			sum = 0; // 초기화
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			for(int j = x1; j <= x2; j++) { // 행별로 구한 누적값에서 범위에 포함되지 않는 부분 제거
				sum += table[j][y2] - table[j][y1-1];
			}
			sb.append(sum + "\n");
		}
		
		System.out.println(sb);
	}
}