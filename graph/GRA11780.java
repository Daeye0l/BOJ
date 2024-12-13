package graph;

import java.io.*;
import java.util.*;

public class GRA11780 {
	static int n;
	static int[][] cost, prev;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		cost = new int[n+1][n+1];
		prev = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(i == j) cost[i][j] = 0;
				else cost[i][j] = 10000000;
			}
		}
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			// 같은 경로라도 입력으로 더 작은 비용이 들어 올 수도 있음
			if(cost[a][b] > c) {
				cost[a][b] = c;
				// 출발점이 a이고 도착점이 b일 때 이전에 거쳐서 온 곳은 a
				prev[a][b] = a;
			}
			
		}
		
		floydWarshall(); // 플로이드 워샬 실행
		addPathToResult(); // 결과에 경로 추가
		
		System.out.println(result);
	}

	private static void floydWarshall() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					// i에서 j로 가는 최단경로 갱신
					if(cost[i][k] + cost[k][j] < cost[i][j]) {
						cost[i][j] = cost[i][k] + cost[k][j];
						// i에서 j로 가는 경로가 갱신된 경우 이전에 거쳐서 온 곳은 prev[k][j]
						prev[i][j] = prev[k][j];
					}
				}
			}
		}
		
		// result에 플로이드 워샬 결과 추가
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(cost[i][j] >= 10000000) result.append(0).append(" ");
				else result.append(cost[i][j]).append(" ");
			}
			result.append("\n");
		}
	}
	
	private static void addPathToResult() {
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				// i에서 j로 가는 경로가 존재하는 경우
				if(i != j && cost[i][j] != 10000000) {
					Stack<Integer> path = traceBack(i, j);
					result.append(path.size()).append(" ");
					while(!path.isEmpty()) result.append(path.pop()).append(" ");
					result.append("\n");
				}
				// i에서 j로 가는 경로가 존재하지 않는 경우 0을 추가
				else result.append(0).append("\n");
			}
		}
	}
	
	private static Stack<Integer> traceBack(int start, int end) {
		Stack<Integer> stack = new Stack<>();
		stack.push(end); // 스택에 도착점 삽입
		
		// 계속 경로를 거슬러 가며 스택에 삽입
		while(start != end) {
			end = prev[start][end];
			stack.push(end);
		}
		
		return stack;
	}
}