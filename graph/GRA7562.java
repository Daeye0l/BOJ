package graph;

import java.io.*;
import java.util.*;

public class GRA7562 {
	static int l, a, b, c, d;
	static int[][] move = {{-1,-2}, {-2,-1}, {-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < t; i++) {
			l = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			c = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs() {
		boolean[][] visited = new boolean[l][l]; // 각 칸의 방문 여부를 저장
		Queue<int[]> que = new LinkedList<>(); // 좌표를 저장하는 큐 생성
		int cnt = 0; // 이동 횟수
		
		visited[a][b] = true; // 시작 지점 방문
		que.add(new int[] {a, b}); // 큐에 삽입
		
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) {
				int[] curr = que.poll(); // 큐에서 좌표 하나 poll
				if(curr[0] == c && curr[1] == d) {
					return cnt;
				}
				for(int j = 0; j < move.length; j++) {
					// 해당 좌표에서 이동할 수 있는 좌표를 계산
					int na = curr[0] + move[j][0];
					int nb = curr[1] + move[j][1];
					// 체스판 범위를 벗어나지 않으면서 방문하지 않은 곳인 경우
					if(na >= 0 && na < l && nb >= 0 && nb < l && !visited[na][nb]) {
						// 방문 후 큐에 삽입
						visited[na][nb] = true;
						que.add(new int[] {na, nb});	
					}
				}
			}
			cnt++;
		}
		return -1;
	}
}