package graph;

import java.io.*;
import java.util.*;

public class GRA1613 {
	static int n;
	static boolean[][] connect;
	static int INF = 400;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		connect = new boolean[n+1][n+1];
		
		// 앞에 번호 사건에서 뒤에 있는 번호의 사건으로 연결됨을 저장
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a][b] = true;
		}
		
		// 플로이드 워샬을 통해 모든 사건에 대해 연결 관계 업데이트
		floydWarshall();
		
		StringBuilder sb = new StringBuilder();
		int s = Integer.parseInt(br.readLine());
		for(int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(connect[a][b]) sb.append(-1);
			// 플로이드 워샬은 모든 사건 쌍 간의 경로 존재 여부를 포함하므로 아래 수행도 가능
			else if(connect[b][a]) sb.append(1);
			else sb.append(0);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void floydWarshall() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(connect[i][k] && connect[k][j]) {
						connect[i][j] = true;
					}
				}
			}
		}
	}
}