package bruteforcing;

import java.io.*;
import java.util.*;

public class BRUTE1018 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n과 m 입력받아 저장
		StringTokenizer st= new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		boolean[][] board = new boolean[n][m]; // n x m의 보드
		
		// 보드 값 채우기
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				if(s.charAt(j) == 'B') 
				board[i][j] = true; // 검은색인 경우 true로
				else board[i][j] = false; // 흰색인 경우 false로 보드 채우기
			}
		}
		
		int count = 0; // 각 경우에 대하여 고쳐야 할 칸의 수를 저장
		int min = 64; // 모든 경우 중 고쳐야 할 칸의 최솟값을 저장
		for(int i = 0; i < n-7; i++) { // 0 ~ n-8
			for(int j = 0; j < m-7; j++) {
				count = check(i, j, board); // 체스판을 따려고 하는 좌상단 좌표와 보드 전달
				min = Math.min(min, count);
			}
		}
		
		System.out.print(min);
	}
	
	private static int check(int x, int y, boolean[][] board) {
		boolean curr = board[x][y]; // 체스판을 따려고 하는 좌상단 좌표의 색
		int count = 0; // 고쳐야 할 칸의 수를 저장
		
		for(int i = x; i < x+8; i++) {
			for(int j = y; j < y+8; j++) {
				if(board[i][j] != curr) count++;
				curr = !curr; // 다음 칸 확인을 위해 값 뒤집기
			}
			curr = !curr; // 다음 줄 확인을 위해 값 뒤집기
		}
		
		// 좌상단 색을 기준으로 할 때 고쳐야 할 개수와 그 반대되는 색을 기준으로 고쳐야 할 개수 중 최소값을 저장
		count = Math.min(count, 64 - count);
		return count;
	}
}