package implementation;

import java.io.*;
import java.util.*;

public class IMPL3190 {
	static int n;
	static int[][] board;
	static HashMap<Integer, Character> map;
	static int[][] move = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		board = new int[n+1][n+1]; // 빈칸: 0, 사과의 위치: 1, 뱀이 차지한 위치: 2
		
		// 사과의 위치 저장
		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			board[a][b] = 1;
		}
		
		// 방향 변환 정보 저장
		int l = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		for(int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			map.put(x, c);
		}
		
		System.out.println(getGameTime());
	}
	
	private static int getGameTime() {
		Deque<Snake> deq = new LinkedList<>();
		deq.offerFirst(new Snake(1, 1, 0));
		board[1][1] = 2;
		
		int time = 0;
		while(true) {
			time += 1;
			
			// 머리를 다음 칸에 이동 시킴, 방향 변환 정보가 있으면 방향도 변경
			Snake head = deq.peekFirst();
			int newX = head.x + move[head.dir][0];
			int newY = head.y + move[head.dir][1];
			int newDir = head.dir;
			if(map.containsKey(time)) {
				if(map.get(time).equals('D')) newDir = (head.dir+1)%4;
				else newDir = (head.dir+3)%4;
			}
			
			// 다음 칸이 벽이거나 자신의 몸이라면 게임 종료
			if(newX <= 0 || newX > n || newY <= 0 || newY > n) break;
			if(board[newX][newY] == 2) break;
			
			// 다음 칸이 사과인 경우, 꼬리는 변함 없음
			if(board[newX][newY] == 1) {
				deq.offerFirst(new Snake(newX, newY, newDir));
				board[newX][newY] = 2;
			}
			// 다음 칸이 사과가 아닌 경우, 꼬리가 위치한 칸을 비움
			else {
				deq.offerFirst(new Snake(newX, newY, newDir));
				board[newX][newY] = 2;
				Snake tail = deq.pollLast();
				board[tail.x][tail.y] = 0;
			}
		}
		
		return time;
	}
	
	private static class Snake {
		private int x;
		private int y;
		private int dir;
		
		Snake(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}
}