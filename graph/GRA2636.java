package graph;

import java.io.*;
import java.util.*;

public class GRA2636 {
	static int n, m;
	static int[][] plate;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int time = 0; // 치즈가 모두 녹기까지 걸린 시간
		int cnt = 0; // 치즈가 놓여있는 칸의 갯수
		plate = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				int state = Integer.parseInt(st.nextToken());
				if(state == 1) cnt++;
				plate[i][j] = state;
			}
		}
		
		while(true) {
			List<int[]> edgeCheeze = bfs();
			if(edgeCheeze.isEmpty()) {
				System.out.println(time);
				System.out.println(cnt);
				return;
			}
			melting(edgeCheeze);
			// 치즈가 모두 녹기 한 시간 전에는 갯수를 남겨야함
			if(cnt-edgeCheeze.size() != 0) {
				cnt -= edgeCheeze.size();
			}
			time++;
		}
	}
	
	// bfs를 통해 가장자리 치즈들의 위치를 반환
	private static List<int[]> bfs() {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		List<int[]> edgeCheeze = new ArrayList<>();
		
		visited[0][0] = true;
		que.offer(new int[] {0, 0});
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			for(int i = 0; i < move.length; i++) {
				int nx = curr[0] + move[i][0];
				int ny = curr[1] + move[i][1];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					if(plate[nx][ny] == 0) {
						visited[nx][ny] = true;
						que.offer(new int[] {nx, ny});	
					}
					else {
						visited[nx][ny] = true;
						edgeCheeze.add(new int[] {nx, ny});
					}
				}
			}
		}
		
		return edgeCheeze;
	}
	
	// 가장자리 치즈를 녹임
	private static void melting(List<int[]> edgeCheeze) {
		for(int[] cheeze : edgeCheeze) {
			plate[cheeze[0]][cheeze[1]] = 0;
		}
	}
}