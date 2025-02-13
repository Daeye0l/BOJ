package graph;

import java.io.*;
import java.util.*;

public class GRA16928 {
	static HashMap<Integer, Integer> map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new HashMap<>(); // 사다리 또는 뱀칸과 연결된 칸을 매핑
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map.put(x, y);
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			map.put(u, v);
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[101];
		int cnt = 0; // 주사위를 굴린 횟수
		
		que.offer(1); // 시작점 큐에 삽입
		visited[1] = true;
		while(!que.isEmpty()) {
			int size = que.size();
			for(int i = 0; i < size; i++) {
				int curr = que.poll();
				if(curr == 100) return cnt;
				List<Integer> nexts = rollDice(curr);
				for(int next : nexts) {
					if(!visited[next]) {
						que.offer(next);
						visited[next] = true;
					}
				}
			}
			// 주사위를 한 번 굴린 경우에 대해 모두 처리 후 cnt 증가
			cnt++;
		}
		
		return 0;
	}
	
	private static List<Integer> rollDice(int n) {
		List<Integer> ret = new ArrayList<>(); // 다음에 이동할 칸들을 저장
		for(int i = 1; i <= 6; i++) {
			if(n+i <= 100) {
				if(map.containsKey(n+i)) ret.add(map.get(n+i));
				else ret.add(n+i);
			}
		}
		return ret;
	}
}
