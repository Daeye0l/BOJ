package implementation;

import java.io.*;
import java.util.*;

public class IMPL18111 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken()); // 인벤토리에 들어 있는 블록의 갯수
		int[][] map = new int[n][m];
		int minTime = Integer.MAX_VALUE;
		int minHeight = 0;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int h = 256; h >= 0; h--) { // 땅 고르기로 맞출 땅의 높이
			int time = 0;
			int block = b;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					// 지형이 기준 높이보다 높은 경우
					if(map[i][j] > h) {
						block += map[i][j]-h; // 블럭을 제거해서 인벤토리에 저장
						time += (map[i][j]-h)*2; // 좌표 하나 당 2초가 걸림
					}
					// 지형이 기준 높이보다 낮은 경우
					else if(map[i][j] < h) {
						block -= h-map[i][j]; // 인벤토리에서 블럭을 꺼내 메움
						time += h-map[i][j]; // 좌표 하나 당 1초가 걸림
					}
				}
			}
			
			if(block >= 0 && time < minTime) { // 인벤토리에 블럭이 남아 있고 더 적은 시간이 걸렸다면 갱신
				minTime = time;
				minHeight = h;
			}
		}
		
		System.out.print(minTime + " " + minHeight);
	}	
}