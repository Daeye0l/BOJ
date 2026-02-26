package graph;

import java.io.*;
import java.util.*;

public class GRA1325 {
	static List<List<Integer>> connections;
	static int[] trustedCnt;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		connections = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			connections.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// B로 인해 A가 해킹되는 것을 모델링 하는 것이 아닌 A가 B를 신뢰하는 것 자체를 모델링  
			connections.get(A).add(B);	
		}
		
		trustedCnt = new int[N+1];
		for(int i = 1; i <= N; i++) {
			visited = new boolean[N+1];
			dfs(i);
		}
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, trustedCnt[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		// 가장 많은 신뢰 받는 컴퓨터를 해킹하는 것이 가장 많은 컴퓨터를 해킹하는 방법임
		for(int i = 1; i <= N; i++) {
			if(trustedCnt[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb);
	}
		
	private static void dfs(int curr) {
		if(visited[curr]) return;
		
		visited[curr] = true;
		for(int next : connections.get(curr)) {
			if(!visited[next]) {
				// 컴퓨터의 신뢰 받는 횟수를 증가
				trustedCnt[next]++;
				dfs(next);
			}
		}
	}
}