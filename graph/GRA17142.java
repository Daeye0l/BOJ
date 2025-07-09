package graph;

import java.io.*;
import java.util.*;

public class GRA17142 {
	static int n, m, emptySpaceCnt;
	static int minTime = Integer.MAX_VALUE;
	static int[][] lab;
	static List<int[]> viruses, selectedViruses;
	static int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		emptySpaceCnt = 0;
		lab = new int[n][n];
		
		viruses = new ArrayList<>();
		selectedViruses = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if(lab[i][j] == 0) emptySpaceCnt++;
				if(lab[i][j] == 2) viruses.add(new int[] {i, j}); 
			}
		}
		
		if(emptySpaceCnt == 0) System.out.println(0);
		else {
			selectVirus(0, 0);
			if(minTime == Integer.MAX_VALUE) System.out.println(-1);
			else System.out.println(minTime);
		}
		
	}
	
	private static void selectVirus(int depth, int idx) {
		// 전체 바이러스에서 m개를 선택하고 나면 퍼뜨림
		if(depth == m) {
			spreadVirus(selectedViruses, emptySpaceCnt);
			return;
		}
		
		// 전체 바이러스 중 m개를 선택해서 조합
		for(int i = idx; i < viruses.size(); i++) {
			selectedViruses.add(viruses.get(i));
			selectVirus(depth+1, i+1);
			selectedViruses.remove(selectedViruses.size()-1);
		}
	}
	
	
	private static void spreadVirus(List<int[]> selectedViruses, int emptySpaceCnt) {
		Queue<Virus> que = new LinkedList<>();
		boolean[][] visited = new boolean[n][n];
			
		for(int[] virus : selectedViruses) {
			visited[virus[0]][virus[1]] = true;
			que.offer(new Virus(virus[0], virus[1], 0));
		}
		
		while(!que.isEmpty()) {
			Virus curr = que.poll();
			
			// 연구소 범위 안에서만 퍼뜨림
			for(int i = 0; i < dirs.length; i++) {
				int nx = curr.x + dirs[i][0];
				int ny = curr.y + dirs[i][1];
				
				if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
				if(lab[nx][ny] == 1 || visited[nx][ny]) continue;
				
				// 이동하는 칸이 0이라면 빈칸의 개수 감소
				if(lab[nx][ny] == 0) emptySpaceCnt--;
				// 빈칸의 개수가 0이라면 최소 시간 갱신(BFS이기 때문에 최소 시간을 보장)
				if(emptySpaceCnt == 0) {
					minTime = Math.min(minTime, curr.time+1);
					return;
				}

				visited[nx][ny] = true;
				que.offer(new Virus(nx, ny, curr.time+1));
			}
		}
	}
	
	private static class Virus {
		private int x;
		private int y;
		private int time;
		
		Virus(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
}