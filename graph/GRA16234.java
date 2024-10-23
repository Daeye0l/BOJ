package graph;

import java.io.*;
import java.util.*;

public class GRA16234 {
	static int n, l ,r;
	static int[][] map;
	static boolean[][] visited;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int days = 0;
		while(true) {
			boolean moved = false;
			visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(!visited[i][j]) {
						if(bfs(i, j)) {
							moved = true;
						}
					}
				}
			}
			// 모든 나라를 탐색하면서 더 이상 인구 이동이 일어나지 않는다면 종료
			if(!moved) break;
			else days++;
		}
		
		System.out.println(days);
	}
	
	private static boolean bfs(int x, int y) {
		List<int[]> union = new ArrayList<>(); // 연합에 포함된 나라의 좌표를 저장
		Queue<int[]> que = new LinkedList<>();
		int sum = map[x][y]; // 연합의 총 인구수
		
		union.add(new int[] {x, y});
		que.offer(new int[] {x, y});
		visited[x][y] = true;
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			for(int i = 0; i < move.length; i++) {
				int nx = curr[0] + move[i][0];
				int ny = curr[1] + move[i][1];
				if(nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny]) {
					int diff = Math.abs(map[curr[0]][curr[1]] - map[nx][ny]);
					if(diff >= l && diff <= r) {
						union.add(new int[] {nx, ny});
						que.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
						sum += map[nx][ny];
					}
				}
			}
		}
		
		// 연합을 형성하지 못한 경우
		if(union.size() == 1) {
			// 인구 이동이 일어나지 않음
			return false;
		}
		// 연합을 형성한 경우
		else {
			for(int[] country : union) {
				// 총 인구를 연합의 크기로 나누어 분배
				map[country[0]][country[1]] = sum / union.size();
			}
			return true;
		}
	}
}