package backTracking;

import java.io.*;
import java.util.*;

public class BT15663 {
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
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		recur(0);
		System.out.println(sb);
	}

	private static void recur(int depth) {
		// 배열에서 수를 m개를 선택한 경우
		if (depth == m) {
			for(int num : temp) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		int prev = 0; // 각 깊이마다 이전값을 초기화
		for (int i = 0; i < n; i++) {
			// 이전값과 비교해서 중복이라면 선택하지 않음
			if (!visited[i] && arr[i] != prev) {
				visited[i] = true;
				temp[depth] = arr[i];
				prev = arr[i]; // 각 깊이마다 이전값 설정
				recur(depth + 1);
				visited[i] = false; // 백트래킹
			}
		}
	}
}