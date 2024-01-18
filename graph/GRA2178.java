package graph;

import java.io.*;
import java.util.*;

public class GRA2178 {
	
	static int[][] maze; // 미로를 저장할 배열
	static int[][] distance; // 각 지점까지의 최단 거리를 저장할 배열
	static int[][] move = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}}; // 인접한 지점으로 이동할 좌표

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N행 M열 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		maze = new int[N+1][M+1];
		distance = new int[N+1][M+1];
		
		// 미로 입력받아서 배열에 저장
		for(int i = 1; i <= N; i++) {
			String s = br.readLine();
			for(int j = 1; j <= M; j++) {
				maze[i][j] = Character.getNumericValue(s.charAt(j-1));
			}
		}
		
		BFS(1, 1, N, M); // 시작점 전달
		
		System.out.println(distance[N][M]);
		
	}
	
	private static void BFS(int x, int y, int N, int M) {
		Queue<Point> que = new LinkedList<>();
		que.add(new Point(x, y)); // 큐에 시작지점 넣기
		distance[x][y] = 1;
		maze[x][y] = 0; // 시작점으로 다시 돌아오는 경우를 막음
		
		while(!que.isEmpty()) {
			Point p = que.remove();
			// 큐에서 뺀 지점에서 상하좌우 좌표를 더해보고
			for(int i = 0; i < 4; i++) {
				int new_x = p.x + move[i][0];
				int new_y = p.y + move[i][1];
				
				// 좌표가 미로를 벗어나지 않으면서
				// 이동할 수 있는 경로라면
				if(new_x >= 1 && new_x <= N &&
				   new_y >= 1 && new_y <= M &&
				   maze[new_x][new_y] == 1) {
					maze[new_x][new_y] = 0;
					que.add(new Point(new_x, new_y)); // 큐에 인접한 지점 추가
					distance[new_x][new_y] = distance[p.x][p.y] + 1; // 최단 거리 갱신
				}
			}
		}
	}
}

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}