package graph;

import java.io.*;
import java.util.*;

public class GRA21736 {
	static int n, m;
	static char[][] campus;
	static boolean[][] visited;
	static int[][] move = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n  = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		campus = new char[n][m];
		int startX = 0;
		int startY = 0;
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				campus[i][j] = s.charAt(j);
				// 도연이의 위치 저장
				if(campus[i][j] == 'I') {
					startX = i;
					startY = j;
				}
			}
		}
		
		int result = bfs(startX, startY);
		if(result != 0) System.out.println(bfs(startX, startY));
		else System.out.println("TT");
	}
	
	private static int bfs(int startX, int startY) {
		int cnt = 0;
		Queue<int[]> que = new LinkedList<>();
		visited = new boolean[n][m];
		
		que.offer(new int[] {startX, startY});
		visited[startX][startY] = true;
		while(!que.isEmpty()) {
			int[] arr = que.poll();
			// 도연이가 사람을 만난 경우 cnt 증가
			if(campus[arr[0]][arr[1]] == 'P') cnt++;
			
			for(int i = 0; i < move.length; i++) {
				int nx = arr[0] + move[i][0];
				int ny = arr[1] + move[i][1];
				// 다음 이동 위치가 벽이 아닌 경우 이전에 방문하지 않았던 곳으로만 이동
				if(nx >= 0 && nx < n && ny >= 0 && ny < m &&
				   campus[nx][ny] != 'X' && !visited[nx][ny]) {
					que.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return cnt;
	}
 }