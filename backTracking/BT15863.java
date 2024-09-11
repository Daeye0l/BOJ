package backTracking;

import java.io.*;
import java.util.*;

public class BT15863 {
	static int n, m, min;
	static int[][] room;
	static List<CCTV> cctvs;
	static int[] modes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE; // 사각지대의 최소 크기를 저장할 변수
		room = new int[n][m];
		cctvs = new ArrayList<>(); // 사무실에 있는 cctv들을 저장할 리스트
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				// cctv를 읿력 받으면 객체 생성후 리스트에 저장
				if(num == 1) cctvs.add(new CCTV(num, i, j, 4));
				else if(num == 2) cctvs.add(new CCTV(num, i, j, 2));
				else if(num == 3) cctvs.add(new CCTV(num, i, j, 4));
				else if(num == 4) cctvs.add(new CCTV(num, i, j, 4));
				else if(num == 5) cctvs.add(new CCTV(num, i, j, 1));
				room[i][j] = num;
			}
		}
		modes = new int[cctvs.size()]; // cctv들의 모드 조합을 저장할 배열
		
		selectMode(0);
		System.out.println(min);
	}
	
	private static void selectMode(int depth) {
		// 모드 조합이 완성되었다면
		if(depth == cctvs.size()) {
			int cnt = 0;
			int[][] temp = copy(room);
			// 각 cctv에 맞는 모드로 감시를 하고
			monitor();
			// 사각지대 개수를 세서
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					if(room[i][j] == 0) cnt++;
				}
			}
			// 최소값을 갱신
			min = Math.min(min, cnt);
			room = temp;
			return;
		}
		
		// 각 cctv로 모드를 조합해서 modes에 저장
		for(int i = 0; i < cctvs.get(depth).cases; i++) {
			modes[depth] = i;
			selectMode(depth + 1);
		}
	}
	
	// 각 cctv에 배당된 모드로 감시 진행
	private static void monitor() { 
		for(int i = 0; i < cctvs.size(); i++) {
			CCTV cctv = cctvs.get(i);
			if(cctv.num == 1) {
				if(modes[i] == 0) up(cctv);
				else if(modes[i] == 1) down(cctv);
				else if(modes[i] == 2) left(cctv);
				else right(cctv);
			}
			else if(cctv.num == 2) {
				if(modes[i] == 0) {up(cctv); down(cctv);}
				else {left(cctv); right(cctv);}
			}
			else if(cctv.num == 3) {
				if(modes[i] == 0) {up(cctv); left(cctv);}
				else if(modes[i] == 1) {down(cctv); left(cctv);}
				else if(modes[i] == 2) {down(cctv); right(cctv);}
				else {up(cctv); right(cctv);}
			}
			else if(cctv.num == 4) {
				if(modes[i] == 0) {up(cctv); left(cctv); right(cctv);}
				else if(modes[i] == 1) {up(cctv); down(cctv); left(cctv);}
				else if(modes[i] == 2) {up(cctv); down(cctv); right(cctv);}
				else {down(cctv); left(cctv); right(cctv);}
			}
			else if(cctv.num == 5) {
				if(modes[i] == 0) up(cctv); down(cctv); left(cctv); right(cctv);
			}
		}
	}
	
	private static void left(CCTV cctv) {
		for(int i = cctv.y-1; i >= 0; i--) {
			if(room[cctv.x][i] == 6) break;
			if(!(room[cctv.x][i] >= 1 && room[cctv.x][i] <= 5)) {
				room[cctv.x][i] = 7;
			}
		}
	}
	
	private static void right(CCTV cctv) {
		for(int i = cctv.y+1; i < m; i++) {
			if(room[cctv.x][i] == 6) break;
			if(!(room[cctv.x][i] >= 1 && room[cctv.x][i] <= 5)) {
				room[cctv.x][i] = 7;
			}
		}
	}
	
	private static void up(CCTV cctv) {
		for(int i = cctv.x-1; i >= 0; i--) {
			if(room[i][cctv.y] == 6) break;
			if(!(room[i][cctv.y] >= 1 && room[i][cctv.y] <= 5)) {
				room[i][cctv.y] = 7;
			}
		}
	}
	
	private static void down(CCTV cctv) {
		for(int i = cctv.x+1; i < n; i++) {
			if(room[i][cctv.y] == 6) break;
			if(!(room[i][cctv.y] >= 1 && room[i][cctv.y] <= 5)) {
				room[i][cctv.y] = 7;
			}
		}
	}
	
	private static int[][] copy(int[][] room) {
		int[][] copy = new int[n][m];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				copy[i][j] = room[i][j];
			}
		}
		return copy;
	}
	
	static class CCTV {
		private int num;
		private int x;
		private int y;
		private int cases;
		
		CCTV(int num, int x, int y, int cases) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.cases = cases;
		}
	}
}