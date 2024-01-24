package graph;

import java.io.*;
import java.util.*;

public class GRA1012 {
	static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	static Cabbage[][] field;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int sum = 0; // 각 케이스에 필요한 지렁이 마리 수
		
			field = new Cabbage[N][M]; // 배추의 위치를 저장할 배열
		
			// 배추의 위치를 입력 받아 저장
			for(int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				field[y][x] = new Cabbage(x, y);
			}
			
			// 배추가 존재하는 위치에서부터 DFS를 실행하여 필요한 지렁이 마리 수를 구함
			for(int j = 0; j < N; j++) {
				for(int l = 0; l < M; l++) {
					if(field[j][l] != null && !field[j][l].visited) {
						DFS(field[j][l], M, N);
						sum += 1;
					}
				}
			}
			
			System.out.println(sum);
		}
	}
	
	// 스택을 이용한 DFS 구현
	private static void DFS(Cabbage root, int M, int N) {
		Stack<Cabbage> stack = new Stack<>();
		stack.push(root);
		root.visited = true;
		
		while(!stack.isEmpty()) {
			Cabbage c = stack.pop();
			
			for(int i = 0; i < 4; i++) {
				int new_x = c.x + move[i][1];
				int new_y = c.y + move[i][0];
				
				if(new_x >= 0 && new_x < M &&
				   new_y >= 0 && new_y < N &&
				   field[new_y][new_x] != null &&
				   !field[new_y][new_x].visited) {
					stack.push(field[new_y][new_x]);
					field[new_y][new_x].visited = true;
				}
			}
		}
	}
}

class Cabbage {
	int x;
	int y;
	boolean visited = false;
	
	public Cabbage(int x, int y) {
		this.x = x;
		this.y = y;
	}
}