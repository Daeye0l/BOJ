package graph;

import java.io.*;
import java.util.*;

public class GRA1507 {
	static int n;
	static int INF = 50000;
	static int[][] time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		time = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				time[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(isPossible()) {
			updateTime();
			System.out.println(getMinSum());
		}
		else System.out.println(-1); // 입력이 유효하지 않은 경우
	}
	
	private static void updateTime() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					// i = k 또는 j = k 또는 i = j인 경우는 중간 지점을 거쳐간다고 할 수 없음 
					if(i != k && j != k && i != j) {
						// i에서 j로 가는 최단 시간에 중간 지점 k가 개입한 경우 i와 j를 연결하는 도로는 필요가 없음
						if(time[i][j] == time[i][k] + time[k][j]) {
							// 도로를 끊음
							time[i][j] = INF;
						}
					}
				}
			}
		}
	}
	
	private static int getMinSum() {
		boolean[][] visited = new boolean[n+1][n+1];
		int sum = 0;
		
		// updateTime을 통해 도로를 끊었기 때문에 선택 가능한 모든 도로를 선택함
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(!visited[i][j] && i != j && time[i][j] != INF) {
					sum += time[i][j];
					// i와 j를 연결하는 도로는 사용함
					visited[i][j] = true;
					visited[j][i] = true;
				}
			}
		}
		
		return sum;
	}
	
	private static boolean isPossible() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(time[i][j] > time[i][k] + time[k][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
}