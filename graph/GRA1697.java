package graph;

import java.io.*;
import java.util.*;

public class GRA1697 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치
		int cnt = 0; // 동생을 찾을 수 있는 가장 빠른 시간
		
		boolean[] visited = new boolean[100001]; // 방문여부를 저장할 배열
			
		Queue<Integer> que = new LinkedList<>();
		que.add(N); // 큐에 시작 위치를 넣음
		int size = que.size();
		
		if(N == K) {
			System.out.println(0);
			return;
		}
		while(true) {
			cnt++; 
			size = que.size();
			// 인접한 위치들 중 방문하지 않았던 위치들을 큐에 넣고 만약 동생의 위치가 나왔다면 출력 후 종료 
			for(int i = 0; i < size; i++) {
				int X = que.remove();
				visited[X] = true;
				
				if(X-1 == K || X+1 == K || 2*X == K) {
					System.out.println(cnt);
					return;
				}
				if(X-1 >= 0 && !visited[X-1]) {
					que.add(X-1);
					visited[X-1] = true;
				}
				if(X+1 <= 100000 && !visited[X+1]) {
					que.add(X+1);
					visited[X+1] = true;
				}
				if(2*X <= 100000 && !visited[2*X]) {
					que.add(2*X);
					visited[2*X] = true;
				}
			}
		}
	}
}