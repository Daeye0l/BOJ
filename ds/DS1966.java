package ds;

import java.io.*;
import java.util.*;

public class DS1966 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트케이스 개수 입력받기
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 문서의 개수
			int M = Integer.parseInt(st.nextToken()); // 궁금한 문서의 위치
					
			Queue<int[]> q = new LinkedList<>(); // 큐 생성
			
			// 문서의 개수 만큼 중요도를 입력받고 큐에 [위치, 중요도]의 배열 저장
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int k = Integer.parseInt(st.nextToken());
				q.add(new int[] {j, k});
			}
			
			int cnt = 0;
			while(true) {
				int[] cur = q.remove(); // 큐에서 가장 앞에 있는 값을 제거 -> cur
				boolean check = false; // cur보다 중요도가 높은 것이 있는지 여부를 저장
				
				for(int[] a : q) { // 큐를 순회하면서
					if(a[1] > cur[1]) { // cur보다 높은 중요도의 문서가 있으면
						check = true;
						break;
					}
				}
				
				if(check) { // cur보다 높은 중요도의 문서가 있는 경우
					q.add(cur); // cur을 다시 큐의 맨 뒤로 넣음
				}
				else { // cur의 중요도가 가장 높은 경우
					cnt++; // 카운트 증가
					if(cur[0] == M) break; // cur의 위치가 M과 같다면 종료
				}
			}
			System.out.println(cnt);
		}
	}
}