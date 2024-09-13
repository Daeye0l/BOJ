package prefixSum;

import java.io.*;
import java.util.*;

public class PS25682 {
	static int n, m, k, min;
	static boolean[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		board = new boolean[n][m];
		
		// 칸이 검은색이라면 true, 흰색이라면 false 저장
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0 ; j < m; j++) {
				if(s.charAt(j) == 'B') board[i][j] = true;
				else board[i][j] = false;
			}
		}
		
		int[][] pSum1 = prefixSum(true);
		int[][] pSum2 = prefixSum(false);
		
		for(int i = 0; i <= n-k; i++) {
			for(int j = 0; j <= m-k; j++) {
				int b = pSum1[i+k][j+k] - pSum1[i+k][j] - pSum1[i][j+k] + pSum1[i][j];
				int w = pSum2[i+k][j+k] - pSum2[i+k][j] - pSum2[i][j+k] + pSum2[i][j];
				min = Math.min(min, Math.min(b,  w));
			}
		}
		
		System.out.println(min);
	}
	
	// 좌측 상단이 color일 때 새로 칠해야 하는 칸의 누적합 배열 반환
	private static int[][] prefixSum(boolean color) {
		int[][] pSum = new int[n + 1][m + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= m; j++) {
				int value = 0;
				// 좌측 상단부터 하여 한칸씩 건너뛴 칸들
				if((i + j) % 2 == 0) {
					// 좌측 상단 색과 다르다면
					if(board[i-1][j-1] != color) {
						value = 1;
					}
				}
				else {
					// 좌측 상단 색과 같다면(달라야 하는데)
					if(board[i-1][j-1] == color) {
						value = 1;
					}
				}
				pSum[i][j] = pSum[i][j-1] + pSum[i-1][j] - pSum[i-1][j-1] + value;
			}
		}
		return pSum;
	}
}