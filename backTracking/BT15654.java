package backTracking;

import java.io.*;
import java.util.*;

public class BT15654 {
	static int n, m;
	static int[] arr, temp;
	static boolean[] visited;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		temp = new int[m];
		visited = new boolean[n];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		recur(0);
		System.out.println(sb);
	}
	
	private static void recur(int depth) {
		// 배열에서 수를 m개 선택하면 결과에 추가 후 종료
		if(depth == m) {
			for(int num : temp) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			// 수를 선택할 때 중복해서 선택하지 않음
			if(!visited[i]) {
				visited[i] = true;
				temp[depth] = arr[i];
				recur(depth+1);
				visited[i] = false; // 백트래킹
			}
		}
	}
}