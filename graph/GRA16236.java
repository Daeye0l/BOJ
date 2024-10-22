package graph;

import java.io.*;
import java.util.*;

public class GRA16236 {
	static int n, size, cnt, startX, startY;
	static int[][] space;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		size = 2; // 현재 상어의 크기
		cnt = 0; // 상어가 먹은 물고기의 수
		startX = -1; // 상어의 위치
		startY = -1; // 상어의 위치
		space = new int[n][n];
	
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				int state = Integer.parseInt(st.nextToken());
				space[i][j] = state;
				// 공간의 상태가 9인 경우 상어의 위치를 저장
				if(state == 9) {
					startX = i;
					startY = j;
					space[i][j] = 0;
				}
			}
		}
		
		int result = 0;
		while(true) {
			int ret = bfs(startX, startY);
			// 먹을 물고기가 더 없는 경우 종료
			if(ret == -1) {
				break; 
			}
			result += ret; // 잡아먹을 수 있는 시간 누적
			cnt++;
			// 물고기를 현재 상어 크기만큼 먹었다면 크기 증가
			if(cnt == size) {
				size++;
				cnt = 0;
			}
		}
		
		System.out.println(result);
	}
	
	private static int bfs(int x, int y) {
		PriorityQueue<Position> que = new PriorityQueue<>(new Comparator<Position>() {
			public int compare(Position p1, Position p2) {
				// 거리가 같고 높이도 같다면 왼쪽에 있는 물고기를 먹음
				if(p1.time == p2.time && p1.x == p2.x) {
					return p1.y - p2.y;
				}
				// 거리만 같다면 위쪽에 있는 물고기를 먹음
				if(p1.time == p2.time) {
					return p1.x - p2.x;
				}
				// 가까운 물고기를 먹음
				return p1.time - p2.time;
			}
		});
		boolean[][] visited = new boolean[n][n];
		
		
		que.offer(new Position(x, y, 0)); // 큐에 현재 상어 위치 삽입
		visited[x][y] = true;
		while(!que.isEmpty()) {
			Position curr = que.poll();
			
			// 현재 위치가 빈 칸이 아니면서 아기 상어의 크기보다 작다면 먹을 수 있는 물고기이다
			if(space[curr.x][curr.y] != 0 && space[curr.x][curr.y] < size) {
				space[curr.x][curr.y] = 0; // 먹었으니 빈 칸으로 변경
				// 먹은 물고기의 위치 저장(해당 위치부터 다시 시작하기 위함)
				startX = curr.x;
				startY = curr.y;
				// 먹은 물고기까지의 시간을 반환
				return curr.time;
			}
			
			for(int i = 0; i < move.length; i++) {
				int nx = curr.x + move[i][0];
				int ny = curr.y + move[i][1];
				// 아직 방문하지 않았으면서 아기 상어보다 크지 않으면 큐에 삽입
				if(nx >= 0 && nx < n && ny >= 0 && ny < n &&
				   !visited[nx][ny] && space[nx][ny] <= size) {
					visited[nx][ny] = true;
					que.offer(new Position(nx, ny, curr.time + 1));
				}
			}
		}
		
		return -1; // 먹을 수 있는 물고기가 없는 경우 -1 반환
	}
	
	private static class Position {
		private int x;
		private int y;
		private int time;
		
		Position(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}