package dp;

import java.io.*;
import java.util.*;

public class DP2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] table = new int[n][3];
		int[] dpMax = new int[3]; // 현재줄까지 각 칸에 누적된 최대 점수
		int[] dpMin = new int[3]; // 현재줄까지 각 칸에 누적된 최소 점수
		
		// 각 줄의 3개의 점수를 배열에 저장
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				table[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음 각 칸의 최댓값과 최솟값은 첫째줄 각 칸의 점수와 동일 
		for(int i = 0; i < 3; i++) {
			dpMax[i] = table[0][i];
			dpMin[i] = table[0][i];
		}
		
		for(int i = 1; i < n; i++) {
			// 이전줄까지 각 칸에 누적된 최대 점수를 가지고
			int tmp0 = dpMax[0];
			int tmp1 = dpMax[1];
			int tmp2 = dpMax[2];
			// 다음줄까지 누적되는 최대 점수 구하기 
			dpMax[0] = Math.max(tmp0, tmp1) + table[i][0];
			dpMax[1] = Math.max(Math.max(tmp0, tmp1), tmp2) + table[i][1];
			dpMax[2] = Math.max(tmp1, tmp2) + table[i][2];
			// 이전줄까지 각 칸에 누적된 최소 점수를 가지고
			tmp0 = dpMin[0];
			tmp1 = dpMin[1];
			tmp2 = dpMin[2];
			// 다음줄까지 누적되는 최소 점수 구하기
			dpMin[0] = Math.min(tmp0, tmp1) + table[i][0];
			dpMin[1] = Math.min(Math.min(tmp0, tmp1), tmp2) + table[i][1];
			dpMin[2] = Math.min(tmp1, tmp2) + table[i][2];
		}
		
		System.out.print(Math.max(Math.max(dpMax[0], dpMax[1]), dpMax[2]) + " ");
		System.out.print(Math.min(Math.min(dpMin[0], dpMin[1]), dpMin[2]));
	}
}