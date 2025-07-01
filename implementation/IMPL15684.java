package implementation;

import java.io.*;
import java.util.*;

public class IMPL15684 {
	static int n, h;
	static boolean[][] ladder;
	static boolean found;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		ladder = new boolean[h+1][n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 세로선 b와 b+1을 잇는 가로선이 a높이에 위치함.
			ladder[a][b] = true;
		}
		
		// 가로선을 0개 ~ 3개까지만 증가하면서 가능한 경우를 찾으면 가로선 갯수를 출력 후 종료
		for(int max = 0; max <= 3; max++) {
			found = false;
			dfs(0, max);
			if(found) {
				System.out.println(max);
				return;
			}
		}
		// 가로선이 3개 초과이거나 찾지 못한 경우
		System.out.println(-1);
	}
	
	private static boolean isPossible(boolean[][] ladder) {
		// 모든 세로선을 탐색
		for(int i = 1; i <= n; i++) {
			// 현재 위치
			int pos = i;
			
			// 가로선이 있는 경우 현재 위치 + 1, 세로선이 있는 경우 현재 위치 -1
			for(int j = 1; j <= h; j++) {
				if(ladder[j][pos]) {
					pos++;
				}
				else if(ladder[j][pos-1]) {
					pos--;
				}
			}
			
			// 끝까지 내려갔을 때 위치가 시작 위치와 다르다면 false 반환
			if(pos != i) {
				return false;
			}
		}
		
		// 모든 세로선이 i번에서 시작해서 i번에서 끝나는 경우
		return true;
	}
	
	private static void dfs(int cnt, int max) {
		if(cnt == max) {
			if(isPossible(ladder)) found = true;
			return;
		}
		
		for(int i = 1; i <= h; i++) {
			for(int j = 1; j < n; j++) {
				// 가능한 경우를 찾으면 더 이상 다른 조합에 대해서는 보지 않아도 됨
				if(found) return;
				
				// 현재 가로선을 놓으려고 하는 위치 좌우에 가로선이 존재한다면 건너뜀
				if(ladder[i][j] || ladder[i][j-1] || ladder[i][j+1]) {
					continue;
				}
				
				// 가로선을 놓고 재귀수행
				ladder[i][j] = true;
				dfs(cnt+1, max);
				ladder[i][j] = false;
			}
		}
	}
}