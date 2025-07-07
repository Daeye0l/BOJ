package implementation;

import java.io.*;
import java.util.*;

public class IMPL15685 {
	static boolean[][] visited;
	static int[][] dirs = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[101][101];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			makeCurve(x, y, d, g);
		}
		
		int cnt = 0;
		for(int i = 0; i <= 99; i++) {
			for(int j = 0; j <= 99; j++) {
				// 네 꼭짓점이 모두 드래곤 커브의 일부인 1x1 정사각형 찾기
				if(visited[i][j] && visited[i][j+1] && visited[i+1][j] && visited[i+1][j+1]) {
					cnt += 1;
				}
			}
		}
		
		System.out.println(cnt);
	}
	
	private static void makeCurve(int x, int y, int d, int g) {
		// 시작점부터 이어지는 드래곤 커브의 방향을 저장
		List<Integer> dirList = new ArrayList<>();
		visited[y][x] = true;
		dirList.add(d);
		
		List<Integer> temp = new ArrayList<>();
		for(int i = 0; i < g; i++) {
			// 드래곤 커브의 방향을 역순으로 저장
			for(int j = dirList.size()-1; j >= 0; j--) {
				temp.add(dirList.get(j));
			}
			
			// 역순으로 저장된 방향을 각각 반시계 방향으로 회전해서 이으면 다음 세대 커브임
			for(int j = 0; j < temp.size(); j++) {
				dirList.add((temp.get(j)+1)%4);
			}
			
			temp.clear();
		}
		
		// 커브에 포함되는 점들을 방문처리
		for(int i = 0; i < dirList.size(); i++) {
			int ny = y + dirs[dirList.get(i)][0];
			int nx = x + dirs[dirList.get(i)][1];
			
			if(ny >= 0 && ny <= 100 && nx >= 0 && nx <= 100
			   && !visited[ny][nx]) {
				visited[ny][nx] = true;
			}
			
			y = ny;
			x = nx;
		}
	}
}