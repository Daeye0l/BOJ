package recursion;

import java.io.*;

public class RECUR1992 {
	static int[][] image;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		image = new int[n][n];
		sb = new StringBuilder();
		String s;
		
		for(int i = 0; i < n; i++) {
			s = br.readLine();
			for(int j = 0; j < n; j++) {
				image[i][j] = s.charAt(j)-48;
			}
		}
		
		recur(0, 0, n);
		System.out.println(sb);
	}
	
	private static void recur(int x, int y, int len) {
		if(!isMixed(x, y, len)) {
			if(image[x][y] == 1) sb.append(1);
			else sb.append(0);
			return;
		}
		// 섞인 경우 분리해야 하므로 여는 괄호 추가
		else {
			sb.append("(");
		}
		
		// 좌상, 우상, 좌하, 우하 순으로 재귀
		recur(x, y, len/2);
		recur(x, y+len/2, len/2);
		recur(x+len/2, y, len/2);
		recur(x+len/2, y+len/2, len/2);
		
		sb.append(")");
	}
	
	// 주어진 영상에 0과 1이 섞여있는지 여부를 반환
	private static boolean isMixed(int x, int y, int len) {
		int color = image[x][y];
		for(int i = x; i < x+len; i++) {
			for(int j = y; j < y+len; j++) {
				if(image[i][j] != color) {
					return true; 
				}
			}
		}
		return false;
	}
}