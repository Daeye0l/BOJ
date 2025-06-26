package implementation;

import java.io.*;
import java.util.*;

public class IMPL23288 {
	static int n, m;
	static int[][] map;
	static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n+1][m+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 윗면이 1이고 동쪽을 바라보는 방향이 3인 주사위 생성
		int[] dice = new int[] {1, 2, 3, 4, 5, 6};
		// 주사위의 처음 위치와 방향 설정
		int x = 1; int y = 1; int dir = 0;

		// k번 이동하며 점수 누적
		int score = 0;
		for(int i = 0; i < k; i++) {
			int nx = x + dirs[dir][0];
			int ny = y + dirs[dir][1];
			
			// 주사위의 이동 방향에 칸이 있는 경우
			if(nx >= 1 && nx <= n && ny >= 1 && ny <= m) {
				// 주사위를 굴리고 점수를 누적함
				roll(dice, dir);
				x = nx;
				y = ny;
				score += getScore(x, y);
				
				// 주사위의 바닥면이 지도에 쓰인 숫자보다 큰 경우, 90도 시계 방향으로 회전
				if(dice[5] > map[x][y]) {
					dir = (dir+1) % 4;
				}
				
				// 주사위의 바닥면이 지도에 쓰인 숫자보다 작은 경우, 90도 반시계 방향으로 회전 
				else if(dice[5] < map[x][y]) {
					dir = (dir+3)%4;
				}
			}
			
			// 주사위의 이동 방향에 칸이 없는 경우
			else {
				// 역방향으로 바꿔서 굴리고 점수를 누적함
				dir = (dir+2) % 4;
				nx = x + dirs[dir][0];
				ny = y + dirs[dir][1];
				roll(dice, dir);
				x = nx;
				y = ny;
				score += getScore(x, y);
				
				// 주사위의 바닥면이 지도에 쓰인 숫자보다 큰 경우, 90도 시계 방향으로 회전
				if(dice[5] > map[x][y]) {
					dir = (dir+1) % 4;
				}
				
				// 주사위의 바닥면이 지도에 쓰인 숫자보다 작은 경우, 90도 반시계 방향으로 회전 
				else if(dice[5] < map[x][y]) {
					dir = (dir+3)%4;
				}
			}
		}
		
		System.out.println(score);
	}
	
	private static void roll(int[] dice, int dir) {
		int[] nextDice = new int[6];
		
		// 동쪽으로 굴린 경우
		if(dir == 0) {
			nextDice[0] = dice[3];
			nextDice[1] = dice[1];
			nextDice[2] = dice[0];
			nextDice[3] = dice[5];
			nextDice[4] = dice[4];
			nextDice[5] = dice[2];
		}
		
		// 남쪽으로 굴린 경우
		else if(dir == 1) {
			nextDice[0] = dice[1];
			nextDice[1] = dice[5];
			nextDice[2] = dice[2];
			nextDice[3] = dice[3];
			nextDice[4] = dice[0];
			nextDice[5] = dice[4];
		}
		
		// 서쪽으로 굴린 경우
		else if(dir == 2) {
			nextDice[0] = dice[2];
			nextDice[1] = dice[1];
			nextDice[2] = dice[5];
			nextDice[3] = dice[0];
			nextDice[4] = dice[4];
			nextDice[5] = dice[3];
		}
		
		// 북쪽으로 굴린 경우
		else {
			nextDice[0] = dice[4];
			nextDice[1] = dice[0];
			nextDice[2] = dice[2];
			nextDice[3] = dice[3];
			nextDice[4] = dice[5];
			nextDice[5] = dice[1];
		}
		
		for(int i = 0; i < 6; i++) {
			dice[i] = nextDice[i];
		}
	}
	
	private static int getScore(int x, int y) {
		int cnt = 1;
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[n+1][m+1];
		
		// (x, y)의 좌표 큐에 삽입
		que.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			
			for(int i = 0; i < dirs.length; i++) {
				int nx = curr[0] + dirs[i][0];
				int ny = curr[1] + dirs[i][1];
				
				// 4방위 중 지도의 범위를 벗어나지 않으면서
				if(nx >= 1 && nx <= n && ny >= 1 && ny <= m
				   && !visited[nx][ny]) {
					// (x, y)와 같은 수가 적혀 있으면 큐에 삽입
					if(map[x][y] == map[nx][ny]) {
						cnt += 1;
						que.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		return cnt * map[x][y];
	}
}