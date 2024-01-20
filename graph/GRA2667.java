package graph;

import java.io.*;
import java.util.*;

public class GRA2667 {
	static int N; // 지도의 크기
	static House[][] map; // 집 객체들을 저장할 지도
	static int complex = 0; // 단지수를 저장할 변수
	static int[][] move = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine()); // 지도의 크기 입력받기
		map = new House[N+1][N+1]; // 집을 저장할 지도 생성
		ArrayList<Integer> houses = new ArrayList<>(); // 단지내 집의 수를 저장
		
		// 각 집 객체를 생성해서 지도에 저장
		for(int i = 1; i <= N; i++) {
			String s = br.readLine();
			for(int j = 1; j <= N; j++) {
				int c = Character.getNumericValue(s.charAt(j-1));
				if(c == 0) map[i][j] = new House(i, j, false);
				else map[i][j] = new House(i, j, true);
			}
		}
		
		// 집이 존재하고 방문하지 않았다면 BFS실행
		for(int i  = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j].exist && !map[i][j].visited) {
					houses.add(BFS(map[i][j]));
					complex += 1;
				}
			}
		}
		
		Collections.sort(houses);
		// 단지수와 단지내 집의 수 출력
		System.out.println(complex);
		for(int i : houses) {
			System.out.println(i);
		}
	}
	
	private static int BFS(House root) {
		Queue<House> que = new LinkedList<>();
		int sum = 0; // 단지내 집의 수를 저장
		
		que.add(root);
		root.visited = true;
		
		while(!que.isEmpty()) {
			House h = que.remove();
			sum += 1;
			
			for(int i = 0; i < 4; i++) {
				int new_x = h.x + move[i][0];
				int new_y = h.y + move[i][1];
				
				if(new_x >= 1 && new_x <= N &&
				   new_y >= 1 && new_y <= N &&
				   map[new_x][new_y].exist &&
				   !map[new_x][new_y].visited) {
					que.add(map[new_x][new_y]);
					map[new_x][new_y].visited = true;
				}
			}
		}
		
		return sum;
	}
}

class House {
	boolean exist; // 집이 존재하는지 여부
	int x; // 지도의 x좌표
	int y; // 지도의 y좌표
	boolean visited = false; // 방문 여부
	
	public House(int x, int y, boolean exist) {
		this.x = x;
		this.y = y;
		this.exist = exist;
	}
}