package graph;

import java.io.*;
import java.util.*;

public class GRA2638 {
	static int n, m;
	static int[][] paper;
	static int[][] arr = {{-1,0}, {1,0}, {0,-1}, {0,1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int cheeseCnt = 0; // 치즈 격자의 총 갯수
		paper = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
				if(paper[i][j] == 1) cheeseCnt++;
			}
		}
		
		int time = 0; // 치즈가 모두 녹아 없어지는데 걸리는 시간
		while(cheeseCnt != 0) { // 치즈가 모두 녹아 없어질 때까지 반복
			List<Cheese> meltedCheese = bfs(); // 녹은 치즈 리스트 반환
			cheeseCnt -= meltedCheese.size(); // 녹은 만큼 갯수 빼주기
			for(Cheese c : meltedCheese) {
				paper[c.x][c.y] = 0;
			}
			time++; // 시간 증가
		}
		
		System.out.println(time);
	}
	
	private static List<Cheese> bfs() {
		Queue<Cheese> que = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];
		List<Cheese> meltedCheese = new ArrayList<>();
		
		paper[0][0] = -1; // 외부 공기와 내부 공기를 구분하기 위해 외부 공기를 -1로 초기화
		visited[0][0] = true;
		que.offer(new Cheese(0, 0));
		while(!que.isEmpty()) {
			Cheese c = que.poll();
			for(int[] d : arr) {
				int nx = c.x + d[0];
				int ny = c.y + d[1];
				if(nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
					// 외부 공기와 2칸 이상 접촉한 치즈라면 녹은 치즈 리스트에 저장
					if(paper[nx][ny] == 1) {
						if(check(nx, ny)) {
							meltedCheese.add(new Cheese(nx, ny));
							visited[nx][ny] = true;
						}
					}
					else {
						paper[nx][ny] = -1;
						visited[nx][ny] = true;
						que.offer(new Cheese(nx, ny));	
					}
				}
			}
		}
		
		return meltedCheese;
	}
	
	// paper[x][y]가 녹는 공간이라면 true 반환 아니라면 false 반환
	private static boolean check(int x, int y) {
		int cnt = 0;
		for(int[] d : arr) {
			int nx = x + d[0];
			int ny = y + d[1];
			// 모눈종이의 맨 가장자리에는 치즈가 놓이지 않으므로 범위 조건은 뺌
			if(paper[nx][ny] == -1) {
				cnt++;
			}
		}
		if(cnt >= 2) return true;
		else return false;
	}
	
	private static class Cheese {
		private int x;
		private int y;
		
		Cheese(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}