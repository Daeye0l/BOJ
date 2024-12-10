package graph;

import java.io.*;
import java.util.*;

public class GRA2458 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 두 a 노드에서 b 노드로 이동할 수 있는 경로가 존재 하는지 여부를 저장
		boolean[][] connect = new boolean[n+1][n+1]; 
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a][b] = true; // a 노드에서 b 노드로 화살표가 생겼으므로 true
		}
		
		// k를 거쳐서 a에서 b로 가는 것 또한 경로가 존재하는 경우 
		for(int k = 1; k <= n; k++) {
			for(int a = 1; a <= n; a++) {
				for(int b = 1; b <= n; b++) {
					if(connect[a][k] && connect[k][b]) {
						connect[a][b] = true;
					}
				}
			}
		}
		
		int result = 0;
		for(int a = 1; a <= n; a++) {
			int cnt = 0;
			for(int b = 1; b <= n; b++) {
				// a에서 b로 가는 경우가 존재하거나 b에서 a로 가는 경우가 존재하는 경우
				if(connect[a][b] || connect[b][a]) {
					cnt++;
				}
			}
			// 자신을 제외한 모든 노드와의 비교 관계를 알 수 있다면 자신의 순위를 예측할 수 있음
			if(cnt == n-1) result++;
		}
		
		System.out.println(result);
	}
}