package dp;

import java.io.*;
import java.util.*;

public class DP1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 집의 수 입력받기
		int[][] arr = new int[n][3]; // 집을 칠하는 비용을 저장할 배열 생성 
		
		// 각 집을 빨강, 초록, 파랑으로 칠하는 비용을 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 배열의 두번째 집부터 마지막 집까지 반복하면서
		for(int i = 1; i < n; i++) {
			// 값을 첫번째 집에서 해당 집까지 칠하는 최소비용으로 바꿔준다.
			for(int j = 0; j < 3; j++) {
				if(j == 0) { // 집을 빨간색으로 칠한 경우
					arr[i][j] = Math.min(arr[i-1][1],arr[i-1][2]) + arr[i][j];
				}
				else if(j == 1) { // 집을 초록색으로 칠한 경우
					arr[i][j] = Math.min(arr[i-1][0], arr[i-1][2]) + arr[i][j];
				}
				else if(j == 2) { // 집을 파란색으로 칠한 경우
					arr[i][j] = Math.min(arr[i-1][0], arr[i-1][1]) + arr[i][j];
				}
			}
		}
		
		System.out.print(Math.min(Math.min(arr[n-1][0], arr[n-1][1]), arr[n-1][2]));
	}
}