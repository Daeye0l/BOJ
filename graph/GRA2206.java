package graph;

import java.io.*;
import java.util.*;

public class GRA2206 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[][] move = {{0,-1}, {0,1}, {-1,0}, {1,0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][M+1];
		visited = new boolean[N+1][M+1][2];
		
		for(int i = 1; i <= N; i++) {
			String s = br.readLine();
			for(int j = 1; j <= M; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j-1));
			}
		}
		
		System.out.println(getMinDistance());
	}
	
	private static int getMinDistance() {
		Queue<Grid> que = new LinkedList<>();
		int distance = 1; // 시작 지점을 포함해서 거리 계산
		visited[1][1][0] = true; // 시작점은 벽을 부수지 않고 방문함
		que.add(new Grid(1, 1, 0)); // 큐에 시작점 삽입
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) {
				Grid curr = que.poll();
				if(curr.x == N && curr.y == M) return distance;
				for(int j = 0; j < 4; j++) {
					int nx = curr.x + move[j][0];
					int ny = curr.y + move[j][1];
					if(nx >= 1 && nx <= N && ny >= 1 && ny <= M) {
						// 다음 지점이 벽이 아닌 경우(현재 상태로 방문한 적이 없어야 함)
						if(map[nx][ny] == 0 && !visited[nx][ny][curr.wall]) {
							visited[nx][ny][curr.wall] = true; // 현재 상태로 방문
							que.add(new Grid(nx, ny, curr.wall)); // 현재 상태로 큐에 추가
						}
						// 다음 지점이 벽인 경우(현재까지 벽을 부순적이 없어야 하고, 벽을 부순 상태로 방문한 적이 없어야 함)
						if(map[nx][ny] == 1 && curr.wall == 0 && !visited[nx][ny][1]) {
							visited[nx][ny][1] = true; // 벽을 부수고 방문
							que.add(new Grid(nx, ny, 1)); // 벽을 부순 상태로 큐에 추가
						}
					}
				}
			}
			distance++;
		}
		return -1; // N, M에 도달하지 못한 경우
	}
	
	private static class Grid {
		private int x;
		private int y;
		private int wall; // 벽이 부숴진 상태라면 1, 아니라면 0
		
		Grid(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}
	}
}