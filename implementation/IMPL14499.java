package implementation;

import java.io.*;
import java.util.*;

public class IMPL14499 {
	static int[][] move = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 지도에 숫자 저장
		int[][] map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 모든 면이 0인 주사위 생성(top, bottom, north, south, west, east 순서)
		int[] dice = new int[] {0, 0, 0, 0, 0, 0};
		StringBuilder result = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			// 현재 주사위의 위치에서 다음 위치 계산
			int dir = Integer.parseInt(st.nextToken());
			int newX = x + move[dir-1][0];
			int newY = y + move[dir-1][1];
			
			// 다음 위치가 지도의 범위를 벗어나지 않는 경우
			if(newX >= 0 && newX < n && newY >= 0 && newY < m) {
				// 주사위를 굴림
				roll(dice, x, y, dir);
				x += move[dir-1][0];
				y += move[dir-1][1];
				
				// 지도에 쓰여진 수가 0이라면 주사위 바닥면에 쓰여진 수를 지도에 복사
				if(map[newX][newY] == 0) {
					map[newX][newY] = dice[1];
				}
				// 지도에 쓰여진 수가 0이 아니라면 지도에 쓰여진 수를 주사위 바닥면에 복사
				else {
					dice[1] = map[newX][newY];
					map[newX][newY] = 0;
				}
				result.append(dice[0]).append("\n");
			}
		}
		
		System.out.println(result);
	}
	
	private static void roll(int[] dice, int x, int y, int dir) {
		int[] newDice = new int[6];
		
		// 동쪽으로 굴린 경우
		if(dir == 1) {
			newDice[0] = dice[4];
			newDice[1] = dice[5];
			newDice[2] = dice[2];
			newDice[3] = dice[3];
			newDice[4] = dice[1];
			newDice[5] = dice[0];
		}
		
		// 서쪽으로 굴린 경우
		else if(dir ==2) {
			newDice[0] = dice[5];
			newDice[1] = dice[4];
			newDice[2] = dice[2];
			newDice[3] = dice[3];
			newDice[4] = dice[0];
			newDice[5] = dice[1];
		}
		
		// 북쪽으로 굴린 경우
		else if(dir == 3) {
			newDice[0] = dice[3];
			newDice[1] = dice[2];
			newDice[2] = dice[0];
			newDice[3] = dice[1];
			newDice[4] = dice[4];
			newDice[5] = dice[5];
		}
		
		// 남쪽으로 굴린 경우
		else {
			newDice[0] = dice[2];
			newDice[1] = dice[3];
			newDice[2] = dice[1];
			newDice[3] = dice[0];
			newDice[4] = dice[4];
			newDice[5] = dice[5];
		}
		
		// 주사위 상태 업데이트
		for(int i = 0; i < 6; i++) {
			dice[i] = newDice[i];
		}
	}
}