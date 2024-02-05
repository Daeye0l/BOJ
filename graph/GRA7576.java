package graph;

import java.io.*;
import java.util.*;

public class GRA7576 {
	static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static int m, n, min;
	static Tomato[][] box;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		min = 0; // 토마토가 모두 익는 최소 날짜
		box = new Tomato[n][m]; // 토마토를 저장할 배열
		List<Tomato> tomatos = new ArrayList<>(); // 익은 토마토를 저장할 리스트
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) { // 토마토를 상자에 저장하면서 익은 토마토라면 리스트에 추가
				box[i][j] = new Tomato(j, i, Integer.parseInt(st.nextToken()));
				if(box[i][j].state == 1) tomatos.add(box[i][j]);
			}
		}
		
		bfs(tomatos); // 익은 토마토들에 대하여 탐색
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(box[i][j].state == 0 && !box[i][j].visited) { // 토마토가 모두 익지 못하는 상황
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(min-1);
	}
	
	private static void bfs(List<Tomato> tomatos) {
		Queue<Tomato> que = new LinkedList<>();
		int size; // 큐의 사이즈
		for(Tomato tomato : tomatos) { // 익은 토마토들을 큐에 넣음
			tomato.visited = true;
			que.add(tomato);
		}
		while(!que.isEmpty()) {
			size = que.size();
			for(int j = 0; j < size; j++) {
				Tomato t = que.remove();
				for(int i = 0; i < 4; i++) {
					int nx = t.x + move[i][0];
					int ny = t.y + move[i][1];
					if(nx >= 0 && nx < m &&
					   ny >= 0 && ny < n &&
					   box[ny][nx].state == 0 &&
					   !box[ny][nx].visited) {
						box[ny][nx].visited = true;
						que.add(box[ny][nx]);
					}
				}
			}
			min += 1; // 현재 큐에 있는 익은 토마토들과 인접한 토마토들을 큐에 다시 집어넣고 날짜 갱신
		}
	}
	
	private static class Tomato {
		private int x;
		private int y;
		private int state;
		private boolean visited;
		
		public Tomato(int x, int y, int state) {
			this.x = x;
			this.y = y;
			this.state = state;
			this.visited = false;
		}
	}
}