package recursion;

import java.io.*;
import java.util.*;

public class RECUR2630 {
	static int[][] paper;
	static int whiteCnt, blueCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];
		whiteCnt = 0;
		blueCnt = 0;
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, n);
		System.out.println(whiteCnt);
		System.out.println(blueCnt);
	}
	
	private static void recur(int x, int y, int len) {
		// 하얀색 종이와 파란색 종이가 섞여있지 않다면 더 이상 자를 필요가 없으므로 개수 증가
		if(!isMixed(x, y, len)) {
			if(paper[x][y] == 1) blueCnt++;
			else whiteCnt++;
			return;
		}
		
		// 4등분 해서 재귀 수행
		recur(x, y, len/2);
		recur(x+len/2, y, len/2);
		recur(x, y+len/2, len/2);
		recur(x+len/2, y+len/2, len/2);
	}
	
	// 해당 영역에 하얀색 종이와 파란색 종이가 섞여 있는지 여부를 반환
	private static boolean isMixed(int x, int y, int len) {
		int color = paper[x][y];
		for(int i = x; i < x + len; i++) {
			for(int j = y; j < y + len; j++) {
				if(paper[i][j] != color) {
					return true;
				}
			}
		}
		return false;
	}
}
