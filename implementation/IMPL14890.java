package implementation;

import java.io.*;
import java.util.*;

public class IMPL14890 {
	static int n, l;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		int cnt = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로길 확인하기
		for(int i = 0; i < n; i++) {
			int[] load = map[i];
			boolean[] used = new boolean[n];
			if(possibleToPass(load, used)) {
				cnt++;
			}
		}
		
		// 세로길 확인하기
		for(int i = 0; i < n; i++) {
			int[] load = new int[n];
			for(int j = 0; j < n; j++) {
				load[j] = map[j][i];
			}
			boolean[] used = new boolean[n];
			if(possibleToPass(load, used)) {
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	private static boolean possibleToPass(int[] load, boolean[] used) {
		for(int i = 0; i < n-1; i++) {
			// 현재 칸과 다음 칸의 높이 차이를 계산
			int diff = Math.abs(load[i+1]-load[i]);
			
			// 두 칸의 높이가 같다면 넘어감
			if(diff == 0) continue;
			
			// 두 칸의 높이 차이가 1인 경우(조건을 봐서 경사로 설치 가능)
			if(diff == 1) {
				// 오르막인 경우
				if(load[i+1] > load[i]) {
					for(int j = i; j > i-l; j--) {
						/* 1. 설치하는 경사로의 범위가 지도의 범위를 벗어나면 안됨
						 * 2. 경사로를 설치하는 땅은 모두 같은 높이여야 함
						 * 3. 이미 경사로를 설치한 땅이 아니어야 함
						*/
						if(j < 0 || load[j] != load[i] || used[j]) return false;
						used[j] = true;
					}
				}
				
				// 내리막인 경우
				else {
					for(int j = i+1; j < i+1+l; j++) {
						if(j >= n || load[j] != load[i+1] || used[j]) return false;
						used[j] = true;
					}
					// 내리막인 경우 i다음 인덱스를 사용했으므로 i의 위치를 경사로를 놓은 마지막 지점으로 이동해 줘야함
					i += l-1;
				}
			}
			
			// 두 칸의 높이 차이가 2인 경우(경사로 설치 불가능)
			else return false;
		}
		
		return true;
	}
}