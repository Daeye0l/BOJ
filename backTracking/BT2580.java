package backTracking;

import java.io.*;
import java.util.*;

public class BT2580 {
	static int[][] board;
	static StringBuilder sb;
	static boolean solved;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[9][9];
		sb = new StringBuilder();
		
		for(int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				int n = Integer.parseInt(st.nextToken());
				board[i][j] = n;
			}
		}
		
		sudoku(0, 0);
		
		System.out.println(sb);
		System.exit(0);
	}
	
	private static void sudoku(int col, int row) {
		// 모든 행을 확인 했다면 정답을 만들고 종료
		if(col == 9) {
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					sb.append(board[i][j] + " ");
				}
				sb.append("\n");
			}
			solved = true;
			return;
		}
		
		// 한 행의 열을 모두 확인했다면
		if(row == 9) { 
			// 다음 행으로 재귀
			sudoku(col+1, 0);
			return;
		}
		
		// 빈칸인 경우
		if(board[col][row] == 0) {
			for(int i = 1; i <= 9; i++) {
				if(isPossible(col, row, i)) {
					board[col][row] = i;
					sudoku(col, row + 1);
					if(solved) return;
					board[col][row] = 0;
				}
			}
			// 빈칸이지만 1 ~ 9까지의 어떤 수도 들어갈 수 없는 경우 종료
			return;
		}
		// 빈칸이 아닌 경우
		else sudoku(col, row + 1);
	}
	
	private static boolean isPossible(int col, int row, int num) {
		int startCol = (col / 3) * 3;
		int startRow = (row / 3) * 3;
		// 열을 고정하고 행에 대해 확인
		for(int i = 0; i < 9; i++) {
			if(board[i][row] == num) {
				return false;
			}
		}
		// 행을 고정하고 열에 대해 확인
		for(int i = 0; i < 9; i++) {
			if(board[col][i] == num) {
				return false;
			}
		}
		// 3x3 정사각형에 대해 확인
		for(int i = startCol; i < startCol + 3; i++) {
			for(int j = startRow; j < startRow + 3; j++) {
				if(board[i][j] == num) {
					return false;
				}
			}
		}
		return true;
	}
}