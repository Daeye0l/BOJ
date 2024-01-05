package greedy;

import java.io.*;
import java.util.*;

public class GR4796 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int cnt = 1;
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 연속하는 P일 중, L일 동안만 사용 가능, 이제 막 V일 짜리 휴가 시작
			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			// 모두 0이라면 종료
			if(L == 0 && P == 0 && V == 0) {
				return;
			}
			else {
				// 연속하는 P일 중 L일 동안 캠핑장을 사용할 수 있을 만큼 우선 사용하고
				// 남은 일수가 L보다 작다면 남은 일을 모두 사용하고 크다면 L만큼만 사용한다.
				int result = (V / P) * L + Math.min(V % P, L);
				System.out.println("Case " + cnt + ": " + result);
				cnt++;
			}
		}
	}
}