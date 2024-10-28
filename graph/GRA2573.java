package graph;

import java.io.*;
import java.util.*;

public class GRA2573 {
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 1;
		while(true) {
			// 더 녹일 빙산이 없는 경우
			if(!melting()) {
				System.out.println(0);
				return;
			}
			// 덩어리의 갯수가 2개 이상인 경우 시간을 출력 후 종료
			if(groupCnt() >= 2) {
				System.out.println(time);
				return;
			}
			time++;
		}
	}
	
	private static boolean melting() {
		List<int[]> list = new ArrayList<>(); // 녹일 칸의 위치와 녹일 갯수로 이뤄진 배열을 저장할 리스트
		for(int i = 1; i < n-1; i++) {
			for(int j = 1; j < m-1; j++) {
				if(map[i][j] != 0) {
					// 녹일 빙산 칸의 위치와 녹일 갯수를 이어서 리스트에 저장
					list.add(new int[] {i, j, seaCnt(i, j)});
				}
			}
		}
		
		// 더 이상 녹일 칸이 없을 경우 false 반환
		if(list.size() == 0) {
			return false;
		}
		
		// 저장된 갯수만큼 칸 녹이기
		for(int[] arr : list) {
			if(map[arr[0]][arr[1]] < arr[2]) map[arr[0]][arr[1]] = 0;
			else map[arr[0]][arr[1]] -= arr[2];
		}
		
		return true;
	}
	
	// 동서남북 방향으로 붙어있는 바닷물의 갯수를 반환
	private static int seaCnt(int x, int y) {
		int cnt = 0;
		for(int i = 0; i < move.length; i++) {
			int nx = x + move[i][0];
			int ny = y + move[i][1];
			if(map[nx][ny] == 0) cnt++;
		}
		return cnt;
	}
	
	// 빙산 덩어리의 갯수를 반환
	private static int groupCnt() {
		visited = new boolean[n][m];
		int cnt = 0;
		for(int i = 1; i < n-1; i++) {
			for(int j = 1; j < m-1; j++) {
				if(map[i][j] != 0 && !visited[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		for(int i = 0; i < move.length; i++) {
			int nx = x + move[i][0];
			int ny = y + move[i][1];
			if(nx >= 0 && nx < n && ny >= 0 && ny < m &&
			   map[nx][ny] != 0 && !visited[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}
}