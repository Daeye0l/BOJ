package implementation;

import java.io.*;
import java.util.*;

public class IMPL12100 {
	static int n, result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		result = 0;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, board);
		System.out.println(result);
	}
	
	private static void dfs(int depth, int[][] board) {
		// 다섯번 이동시킬 때마다 최댓값 갱신
		if(depth == 5) {
			int max = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					max = Math.max(max, board[i][j]);
				}
			}
			result = Math.max(result, max);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int[][] copyBoard = copy(board);
			if(i == 0) moveUp(copyBoard);
			else if(i == 1) moveDown(copyBoard);
			else if(i == 2) moveLeft(copyBoard);
			else moveRight(copyBoard);
			dfs(depth+1, copyBoard);
		}
	}
	
	private static void moveUp(int[][] board) {
		for(int	col = 0; col < n; col++) {
			// 대상이 되는 줄에서 0이 아닌 숫자들을 저장
			List<Integer> line = new ArrayList<>();
			
			// 열 인덱스를 고정하고 행을 따라 0이 아닌 수를 리스트에 저장
			for(int row = 0; row < n; row++) {
				if(board[row][col] != 0) {
					line.add(board[row][col]);
				}
			}
			
			// 숫자를 앞에서부터 한번씩만 합치기
			List<Integer> mergedLine = new ArrayList<>();
			int i = 0;
			while(i < line.size()) {
				if(i+1 < line.size() && line.get(i).equals(line.get(i+1))) {
					mergedLine.add(line.get(i)*2);
					i+=2;
				}
				else {
					mergedLine.add(line.get(i));
					i++;
				}
			}
			
			// 이동시킨 결과를 보드에 다시 저장
			for(int row = 0; row < n; row++) {
				if(row < mergedLine.size()) board[row][col] = mergedLine.get(row);
				else board[row][col] = 0;
			}
		}
	}
	
	private static void moveDown(int[][] board) {
		for(int col = 0; col < n; col++) {
			List<Integer> line = new ArrayList<>();
			
			// 열 인덱스를 고정하고 행을 따라 0이 아닌 수를 리스트에 저장
			for(int row = n-1; row >= 0; row--) {
				if(board[row][col] != 0) {
					line.add(board[row][col]);
				}
			}
			
			// 숫자를 앞에서부터 한번씩만 합치기
			List<Integer> mergedLine = new ArrayList<>();
			int i = 0;
			while (i < line.size()) {
				if (i+1 < line.size() && line.get(i).equals(line.get(i+1))) {
					mergedLine.add(line.get(i) * 2);
					i += 2;
				} else {
					mergedLine.add(line.get(i));
					i++;
				}
			}
			
			// 이동시킨 결과를 보드의 뒤부터 저장
			int size = mergedLine.size();
            for(int row = n-1; row >= 0; row--) {
                int j = n-1-row;
                if(j < size) board[row][col] = mergedLine.get(j);
                else board[row][col] = 0;
            }
		}
	}
	
	private static void moveLeft(int[][] board) {
		for(int row = 0; row < n; row++) {
			List<Integer> line = new ArrayList<>();
			
			// 행 인덱스를 고정하고 열을 따라 0이 아닌 수를 리스트에 저장
			for(int col = 0; col < n; col++) {
				if(board[row][col] != 0) {
					line.add(board[row][col]);
				}
			}
			
			// 숫자를 앞에서부터 한번씩만 합치기
			List<Integer> mergedLine = new ArrayList<>();
			int i = 0;
			while (i < line.size()) {
				if (i+1 < line.size() && line.get(i).equals(line.get(i+1))) {
					mergedLine.add(line.get(i) * 2);
					i += 2;
				} else {
					mergedLine.add(line.get(i));
					i++;
				}
			}
			
			// 이동시킨 결과를 보드에 다시 저장
			for(int col = 0; col < n; col++) {
				if(col < mergedLine.size()) board[row][col] = mergedLine.get(col);
				else board[row][col] = 0;
			}
		}
	}
	
	private static void moveRight(int[][] board) {
		for(int row = 0; row < n; row++) {
			List<Integer> line = new ArrayList<>();
			
			// 행 인덱스를 고정하고 열을 따라 0이 아닌 수를 리스트에 저장
			for(int col = n-1; col >= 0; col--) {
				if(board[row][col] != 0) {
					line.add(board[row][col]);
				}
			}
			
			// 숫자를 앞에서부터 한번씩만 합치기
			List<Integer> mergedLine = new ArrayList<>();
			int i = 0;
			while (i < line.size()) {
				if (i+1 < line.size() && line.get(i).equals(line.get(i+1))) {
					mergedLine.add(line.get(i) * 2);
					i += 2;
				} else {
					mergedLine.add(line.get(i));
					i++;
				}
			}
			
			// 이동시킨 결과를 보드의 뒤부터 저장
			int size = mergedLine.size();
            for(int col = n-1; col >= 0; col--) {
                int j = n-1-col;
                if(j < size) board[row][col] = mergedLine.get(j);
                else board[row][col] = 0;
            }
		}
	}
	
	private static int[][] copy(int[][] board) {
		int[][] copyBoard = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		return copyBoard;
	}
}