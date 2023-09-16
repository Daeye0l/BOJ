package dp;

import java.io.*;
import java.util.*;

public class DP12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물품의 수
		int k = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게
		int[] w = new int[n+1]; // 물품의 무게를 저장
		int[] v = new int[n+1]; // 물품의 가치를 저장
		
		int[][]dp = new int[n+1][k+1]; // 1 ~ i번째 물건을 선택하여 K의 무게만큼 채울 때 최대 가치를 저장
		
		// 각 물품의 무게와 가치를 입력받아 배열에 저장
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= k; j++) {
				if(w[i] > j) { // i번째 물건이 버틸 수 있는 무게보다 크다면
					// i번째 물건은 집어넣을 수 없으므로 1 ~ i-1번째 물건으로 최대 가치를 만들고
					dp[i][j] = dp[i-1][j];
				}
				else { // i번째 물건이 버틸 수 있는 무게보다 작거나 같다면
					// i번째 물건을 선택하여 나머지 1 ~ i-1번째 물건으로 최대가치가 되게 채우거나
					// i번째 물건을 선택하지 않고 1 ~ i-1번째 물건으로 최대가치가 되게 채운 값 중 큰 것을 선택하여 채운다.
					dp[i][j] = Math.max(dp[i-1][j], v[i] + dp[i-1][j - w[i]]);
				}	
			}
		}
		
		System.out.print(dp[n][k]);
	}
}