package implementation;

import java.io.*;
import java.util.*;

public class IMPL12100_2 {
	static int n, bigBlock;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		bigBlock = 0;
		int[][] board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, board);
		System.out.println(bigBlock);
	}
	
	private static void dfs(int depth, int[][] board) {
		// 5번 이동하고 나면 가장 큰 블록 갱신
		if(depth == 5) {
			int max = 0;
			
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					max = Math.max(max, board[i][j]);
				}
			}
			
			bigBlock = Math.max(bigBlock, max);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int[][] copyBoard = copy(board);
			move(copyBoard, i);
			dfs(depth+1, copyBoard);
		}
	}
	
	private static void move(int[][] board, int dir) {
		// 위로 이동시키는 경우
		if(dir == 0) {
			for(int i = 0; i < n; i++) {
				List<Integer> line = new ArrayList<>();
				
				// 각 라인에서 0이 아닌 숫자를 리스트에 저장
				for(int j = 0; j < n; j++) {
					if(board[j][i] != 0) line.add(board[j][i]);
				}
				
				// 각 라인을 이동시켜서 합치기
				List<Integer> mergedLine = merge(line);
				
				// 합친 결과를 다시 원래 라인에 저장
				for(int j = 0; j < n; j++) {
					if(j < mergedLine.size()) {
						board[j][i] = mergedLine.get(j); 
					}
					else board[j][i] = 0;
				}
			}
		}
		
		// 아래로 이동시키는 경우
		else if(dir == 1) {
			// 각 라인에서 0이 아닌 숫자를 리스트에 저장
			for(int i = 0; i < n; i++) {
				List<Integer> line = new ArrayList<>();
				
				for(int j = n-1; j >= 0; j--) {
					if(board[j][i] != 0) line.add(board[j][i]);
				}
			
				// 각 라인을 이동시켜서 합치기
				List<Integer> mergedLine = merge(line);
			
				// 합친 결과를 다시 원래 라인에 저장
				int idx = 0;
				for(int j = n-1; j >= 0; j--) {
					if(idx < mergedLine.size()) {
						board[j][i] = mergedLine.get(idx);
						idx++;
					}
					else board[j][i] = 0;
				}
			}
		}
		
		// 왼쪽으로 이동시키는 경우
		else if(dir == 2) {
			for(int i = 0; i < n; i++) {
				List<Integer> line = new ArrayList<>();
				
				// 각 라인에서 0이 아닌 숫자를 리스트에 저장
				for(int j = 0; j < n; j++) {
					if(board[i][j] != 0) line.add(board[i][j]);
				}
				
				// 각 라인을 이동시켜서 합치기
				List<Integer> mergedLine = merge(line);
				
				// 합친 결과를 다시 원래 라인에 저장
				for(int j = 0; j < n; j++) {
					if(j < mergedLine.size()) {
						board[i][j] = mergedLine.get(j);
					}
					else board[i][j] = 0;
				}
			}
		}
		
		// 오른쪽으로 이동시키는 경우
		else {
			for(int i = 0; i < n; i++) {
				List<Integer> line = new ArrayList<>();
				
				// 각 라인에서 0이 아닌 숫자를 리스트에 저장
				for(int j = n-1; j >= 0; j--) {
					if(board[i][j] != 0) line.add(board[i][j]);
				}
				
				// 각 라인을 이동시켜서 합치기
				List<Integer> mergedLine = merge(line);
				
				// 합친 결과를 다시 원래 라인에 저장
				int idx = 0;
				for(int j = n-1; j >= 0; j--) {
					if(idx < mergedLine.size()) {
						board[i][j] = mergedLine.get(idx);
						idx++;
					}
					else board[i][j] = 0;
				}
			}
		}
	}
	
	private static List<Integer> merge(List<Integer> line) {
		List<Integer> mergedLine = new ArrayList<>();
		
		// 앞에서부터 같은 숫자가 있는 경우 합쳐서 리스트에 추가
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
		
		return mergedLine;
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