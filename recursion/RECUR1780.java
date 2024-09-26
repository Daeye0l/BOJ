package recursion;

import java.io.*;
import java.util.*;

public class RECUR1780 {
	static int[][] paper;
	static int cntMinusOne, cntZero, cntOne;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		cntMinusOne = 0; cntZero = 0; cntOne = 0;
		StringTokenizer st;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, n);
		System.out.println(cntMinusOne);
		System.out.println(cntZero);
		System.out.println(cntOne);
	}
	
	private static void recur(int x, int y, int len) {
		// 주어진 영역이 한 숫자로만 이루어진 경우 개수를 증가
		if(!isMixed(x, y, len)) {
			if(paper[x][y] == -1) cntMinusOne++;
			else if(paper[x][y] == 0) cntZero++;
			else cntOne++;
			return;
		}
		
		// 숫자들이 섞여있는 경우 9가지 영역에 대해 재귀 수행
		int nextLen = len/3;
		for(int i = x; i < x+len; i += nextLen) {
			for(int j = y; j < y+len; j += nextLen) {
				recur(i, j, nextLen);
			}
		}
	}
	
	// 주어진 영역에 수가 섞여있는지 여부를 반환
	private static boolean isMixed(int x, int y, int len) {
		int num = paper[x][y];
		for(int i = x; i < x+len; i++) {
			for(int j = y; j < y+len; j++) {
				if(paper[i][j] != num) {
					return true;
				}
			}
 		}
		return false;
	}
}