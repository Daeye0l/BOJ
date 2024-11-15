package graph;

import java.io.*;
import java.util.*;

public class GRA5014 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int u = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int result = bfs(f, s, g, u, d);
		if(result != -1) System.out.println(result);
		else System.out.println("use the stairs");
	}
	
	private static int bfs(int f, int s, int g, int u, int d) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[f+1];
		int cnt = 0;
	
		visited[s] = true;
		que.offer(s); // 처음 위치 큐에 삽입
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) {
				int curr = que.poll();
				// G층에 도착했다면 버튼을 누른 횟수 반환
				if(curr == g) return cnt;
			
				// 현재 층에서 U층 위의 층수와 D층 아래 층수
				int uNext = curr+u;
				int dNext = curr-d;
				
				// 다음으로 갈 층을 방문하지 않았다면 방문 후 큐에 삽입
				if(uNext >= 1 && uNext <= f && !visited[uNext]) {
					visited[uNext] = true;
					que.offer(uNext);
				}
				if(dNext >= 1 && dNext <= f && !visited[dNext]) {
					visited[dNext] = true;
					que.offer(dNext);
				}
			}
			cnt++;
		}
		// 엘리베이터로 이동할 수 없는 경우 -1 반환
		return -1;
	}
}