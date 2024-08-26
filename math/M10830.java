package math;

import java.io.*;
import java.util.*;

public class M10830 {
	static int[][] originMatrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		// B제곱할 행렬 입력받기
		originMatrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				originMatrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		// 결과 출력
		int[][] result = fasterPow(originMatrix, b);
		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result[0].length; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static int[][] mulMatrix (int[][] matrixA, int[][] matrixB) {
		// 결과 행렬은 행렬 A의 행, 행렬 B의 열 크기를 가지고 있다.
		int[][] matrixC = new int[matrixA.length][matrixB[0].length];
		for(int i = 0; i < matrixA.length; i++) {
			for(int j = 0; j < matrixB[0].length; j++) {
				for(int k = 0; k < matrixA[0].length; k++) {
					matrixC[i][j] += matrixA[i][k] * matrixB[k][j];
					matrixC[i][j] %= 1000;
				}
			}
		}
		return matrixC;
	}
	
	// 빠른 거듭제곱
	private static int[][] fasterPow(int[][] matrix, long b) {
		// 지수가 1이라면 제곱할 것이 없으므로 원래 행렬 반환
		if(b == 1) {
			return matrix;
		}
		// 지수를 반으로 나눈 제곱꼴 행렬에 대해 재귀적으로 계산
		int[][] tmp = fasterPow(matrix, b / 2);
		tmp = mulMatrix(tmp, tmp);
		if(b % 2 != 0) { // 지수가 홀수라면 제곱한 제곱꼴에 처음 행렬을 곱한 행렬을 반환
			return mulMatrix(tmp, originMatrix);
		}
		else { // 지수가 짝수라면 제곱꼴 행렬만 반환
			return tmp;
		}
	}
}