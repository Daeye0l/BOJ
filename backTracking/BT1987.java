package backTracking;

import java.io.*;
import java.util.*;

public class BT1987 {
	static int r, c, max;
	static char[][] board;
	static List<Character> visited;
	static int[][] arr = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		max = 0;
		board = new char[r][c];
		visited = new ArrayList<>();
		
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		move(0, 0, 1);
		System.out.println(max);
	}
	
	
	private static void move(int x, int y, int cnt) {
		visited.add(board[x][y]); // 방문한 위치의 문자를 저장
		
		for(int i = 0; i < 4; i++) {
			int nx = x + arr[i][0];
			int ny = y + arr[i][1];
			// 상하좌우 중 하나로 이동할 수 있다면 이동
			if(nx >= 0 && nx < r && ny >= 0 && ny < c
			   && !visited.contains(board[nx][ny])) {
				move(nx, ny, cnt+1);
			}
		}
		
		// 상하좌우 중 이동할 수 있는 곳이 없다면 최대값 갱신 후 종료
		max = Math.max(max, cnt);
		visited.remove(visited.size()-1);
		return;
	}
}