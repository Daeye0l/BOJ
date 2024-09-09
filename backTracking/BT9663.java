package backTracking;

import java.io.*;

public class BT9663 {
	static int n, cnt;
	static int[] arr; // arr[i]는 i행에 놓인 열의 번호
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		dfs(0);
		System.out.println(cnt);
	}
	
	// 각 행을 depth로 하여 dfs 
	private static void dfs(int depth) {
		if(depth == n) { // 모든 행에 퀸을 배치 했다면 경우의 수 증가 후 종료
			cnt++;
			return;
		}
		for(int i = 0; i < n; i++) {
			// depth행의 i번째 열에 퀸을 배치하고
			arr[depth] = i;
			// 그곳이 배치 가능한 곳이라면 다음 행에 대해 dfs
			if(possible(depth)) {
				dfs(depth + 1);
			}
		}
	}
	
	private static boolean possible(int col) {
		for(int i = 0; i < col; i++) {
			// 같은 열에 퀸이 위치하는지 확인
			if(arr[i] == arr[col]) {
				return false;
			}
			
			// 행의 차와 열의 차가 같은지 즉 대각선 상에 퀸에 위치하는지 확인
			else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
				return false;
			}
		}
		return true;
	}
}