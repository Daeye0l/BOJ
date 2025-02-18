package bruteforcing;

import java.io.*;
import java.util.*;

public class BRUTE14500 {
	static int n, m;
	static int max = 0;
	static int[][] paper;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		paper = new int[n][m];
		visited = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				// 1~4번 테트로미노 확인
				dfs(i, j, 1, paper[i][j]);
				// 5번 테트로미노 따로 확인
				checkTetFive(i, j);
			}
		}
		
		System.out.println(max);
	}
	
	// 1~4번 테트로미노는 dfs로 확인 가능
	private static void dfs(int x, int y, int depth, int sum) {
		if(depth == 4) {
			max = Math.max(max, sum);
			return;
		}
		int[][] arr = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		visited[x][y] = true;
		for(int i = 0; i < arr.length; i++) {
			int nx = x + arr[i][0];
			int ny = y + arr[i][1];
			if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
				dfs(nx, ny, depth+1, sum+paper[nx][ny]);
			}
		}
		visited[x][y] = false;
	}
	
	// 5번 테트로미노를 따로 확인
	private static void checkTetFive(int x, int y) {
		// x, y를 중심으로 한 5번 테트로미노 모양
		int[][][] shapes = {{{0,-1}, {0,1}, {1,0}}, // ㅜ 
				           {{0,-1}, {0,1}, {-1,0}}, // ㅗ
				           {{0,-1}, {-1,0}, {1,0}}, // ㅓ
				           {{0,1}, {-1,0}, {1,0}}}; // ㅏ
		for(int[][] shape : shapes) {
			int sum = paper[x][y];
			boolean isPossible = true;
			for(int i = 0; i < shape.length; i++) {
				int nx = x + shape[i][0];
				int ny = y + shape[i][1];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					sum += paper[nx][ny];
				}
				// x, y가 중심이기 때문에 나머지 폴리오미노들의 좌표가 범위에 없다면 만들기 불가능
				else {
					isPossible = false;
					break;
				}
			}
			if(isPossible) max = Math.max(max, sum);
		}
	}
}