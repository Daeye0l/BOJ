package dp;

import java.io.*;
import java.util.*;

public class DP1106 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		// dp[i] = i명을 늘릴 때, 비용의 최솟값
		// 적어도 C명을 늘여야 한다는 말은 C명 이상을 늘여도 된다는 뜻이므로 얻을 수 있는 고객수의 최대값인 +100까지 확인
		int[] dp = new int[C+101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		// 0명의 고객을 늘릴때 드는 비용은 없음
		dp[0] = 0; 
		// 입력받는 n개의 정보에 대해 확인
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			
			// n번 도시를 홍보할 때 드는 비용과 얻는 고객 수
			int cost = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			
			for(int i = cnt; i < C+101; i++) {
				// 지금까지 주어진 정보로 만들 수 없는 고객수인 경우는 제외
				if(dp[i-cnt] != Integer.MAX_VALUE) {
					dp[i] = Math.min(dp[i], dp[i-cnt] + cost);	
				}
			}
		}
		
		// C명 이상의 범위에서 최솟값을 찾음
		int result = Integer.MAX_VALUE;
		for(int i = C; i < C+101; i++) {
			result = Math.min(result, dp[i]);
		}
		
		System.out.println(result);
	}
}