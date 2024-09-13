package prefixSum;

import java.io.*;
import java.util.*;

public class PS2167 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n+1][m+1];
		
		// (1, 1)부터 (n, m)까지 정수 배열 입력
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각 칸에 대하여 누적합 계산하기
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				// 더할 때 중복으로 적용되는 (i-1, j-1)칸은 한 번 빼주기
				arr[i][j] += arr[i][j-1] + arr[i-1][j] - arr[i-1][j-1];
			}
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int l = 0; l < k; l++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 뺄 때 중복으로 빠진 (i-1, j-1)칸은 한 번 더해주기
			System.out.println(arr[x][y] - arr[i-1][y] - arr[x][j-1] + arr[i-1][j-1]);
		}
		
	}
}