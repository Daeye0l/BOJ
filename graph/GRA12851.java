package graph;

import java.io.*;
import java.util.*;

public class GRA12851 {
	static int min, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		cnt = 0;
		
		bfs(n, k);
		System.out.println(min);
		System.out.println(cnt);
	}
	
	private static void bfs(int start, int end) {
		Queue<Integer> que = new LinkedList<>();
		int[] time = new int[100001];
		Arrays.fill(time, -1);
		
		que.offer(start);
		time[start] = 0;
		
		while(!que.isEmpty()) {
			int curr = que.poll();
			if(curr == end) {
				min = time[curr];
				cnt++;
			}
			for(int i = 0; i < 3; i++) {
				// 현재 위치에서 다음 위치 계산
				int next;
				if(i == 0) next = curr+1;
				else if(i == 1) next = curr-1;
				else next = curr*2;
				// 위치가 범위 안에 들어오는 경우만
				if(next >= 0 && next <= 100000) {
					// 다음 위치가 한 번도 방문하지 않았거나 같은 시간에 방문하는 다른 경우의 수라면 큐에 추가
					if(time[next] == -1 || time[next] == time[curr] + 1) {
						que.offer(next);
						time[next] = time[curr]+1;
					}
				}
			}
		}
	}
}