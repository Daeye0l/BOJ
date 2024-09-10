package backTracking;

import java.io.*;
import java.util.*;

public class BT14889 {
	static int n, min;
	static int[][] ability;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		ability = new int[n][n];
		visited = new boolean[n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeTeam(0, 0);
		System.out.println(min);
	}
	
	private static void makeTeam(int num, int cnt) {
		// 재귀 수행을 총 인원의 절반을 하고 나면
		if(cnt == n/2) {
			int teamA = 0;
			int teamB = 0;
			// 같은 팀인 인원들끼리의 능력치를 모두 더하고
			for(int i = 0; i < n-1; i++) {
				for(int j = i + 1; j < n; j++) {
					if(visited[i] == true && visited[j] == true) {
						teamA += ability[i][j] + ability[j][i];
					}
					else if(visited[i] == false && visited[j] == false) {
						teamB += ability[i][j] + ability[j][i];
					}
				}
			}
			// 차를 구한 후
			int diff = Math.abs(teamA - teamB);
			// 최소값을 갱신한다
			min = Math.min(min, diff);
		}
		
		// n명의 인원으로 가능한 팀 조합을 재귀적으로 생성
		for(int i = num; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				makeTeam(i + 1, cnt + 1);
				visited[i] = false;
			}
		}
	}
}