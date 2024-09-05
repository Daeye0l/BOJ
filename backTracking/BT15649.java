package backTracking;

import java.io.*;
import java.util.*;

public class BT15649 {
	static int n, m;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m]; // 출력할 M개의 자연수 수열을 저장
		visited = new boolean[n+1]; // 1 ~ N번 수의 방문 여부를 저장
		
		dfs(0); // 깊이 0에서 시작
	}
	
	private static void dfs(int depth) {
		// 기저조건
		if(depth == m) { // 깊이가 원하는 만큼 다 도착한 경우
			// 수열의 내용을 출력하고 종료
			for(int i = 0; i < m; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		// 1 ~ N번 수를 사용하여 재귀 수행으로 수열 생성
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				dfs(depth + 1);
				visited[i] = false;
			}
		}
	}
}