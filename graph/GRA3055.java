package graph;

import java.io.*;
import java.util.*;

public class GRA3055 {
	static int r, c;
	static char[][] forest;
	static boolean[][] visited;
	static int[] cave;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		forest = new char[r][c];
		
		Queue<Hedgehog> hedgehogQue = new LinkedList<>();
		Queue<Water> waterQue = new LinkedList<>(); 
		for(int i = 0; i < r; i++) {
			String s = br.readLine();
			for(int j = 0; j < c; j++) {
				char c = s.charAt(j);
				forest[i][j] = c;
				// 고슴도치의 위치라면 큐에 저장하고 빈 칸으로 만들어 물이 이동할 수 있도록 변경
				if(c == 'S') {
					hedgehogQue.offer(new Hedgehog(i, j, 0));
					forest[i][j] = '.';
				}
				// 물의 위치도 큐에 저장
				if(c == '*') {
					waterQue.offer(new Water(i, j));
				}
				// 비버굴의 위치도 저장
				if(c == 'D') cave = new int[] {i, j};
			}
		}
		
		int result = bfs(hedgehogQue, waterQue);
		if(result == -1) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	private static int bfs(Queue<Hedgehog> hedgehogQue, Queue<Water> waterQue) {
		visited = new boolean[r][c]; // 고슴도치의 방문 여부를 저장
		
		while(!hedgehogQue.isEmpty()) {
			waterQue = flow(waterQue); // 물을 먼저 퍼뜨림
			
			int size = hedgehogQue.size();
			for(int i = 0; i < size; i++) {
				Hedgehog hedgehog = hedgehogQue.poll();
				// 비버굴에 도착한 경우 시간 반환
				if(hedgehog.x == cave[0] && hedgehog.y == cave[1]) {
					return hedgehog.time;
				}
				for(int j = 0; j < move.length; j++) {
					int nx = hedgehog.x + move[j][0];
					int ny = hedgehog.y + move[j][1];
					if(nx >= 0 && nx < r && ny >= 0 && ny < c
					   && (forest[nx][ny] == '.' || forest[nx][ny] == 'D')
					   && !visited[nx][ny]) {
						// 고슴도치가 다음으로 이동할 위치가 물이 퍼지는 위치인지 확인
						boolean isPossible = true;
						for(Water water : waterQue) {
							if(water.x == nx && water.y == ny) {
								isPossible = false;
							}
						}
						// 이동할 수 있다면 방문하고 큐에 추가
						if(isPossible) {
							visited[nx][ny] = true;
							hedgehogQue.offer(new Hedgehog(nx, ny, hedgehog.time+1));
						}
					}
				}
			}
		}
		return -1;
	}
	
	private static Queue<Water> flow(Queue<Water> que) {
		Queue<Water> nextQue = new LinkedList<>();
		while(!que.isEmpty()) {
			Water curr = que.poll();
			for(int i = 0; i < move.length; i++) {
				int nx = curr.x + move[i][0];
				int ny = curr.y + move[i][1];
				// 다음으로 물이 퍼질 수 있는 곳의 위치를 큐에 저장 후 반환
				if(nx >= 0 && nx < r && ny >= 0 && ny < c
				   && forest[nx][ny] == '.') {
					forest[nx][ny] = '*';
					nextQue.offer(new Water(nx, ny));
				}
			}
		}
		return nextQue;
	}
	
	private static class Hedgehog {
		private int x;
		private int y;
		private int time;
		
		Hedgehog(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	private static class Water {
		private int x;
		private int y;
		
		Water(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}