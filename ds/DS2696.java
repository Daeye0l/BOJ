package ds;

import java.io.*;
import java.util.*;

public class DS2696 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int m = Integer.parseInt(br.readLine()); // 수열의 크기
			PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder()); // 수열의 절반에 해당하는 작은 수를 저장
			PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 수열의 나머지 절반에 해당하는 큰 수를 저장
			
			int cnt = 0; // 중앙값의 갯수
			StringBuilder result = new StringBuilder(); // 중앙값들을 저장
			
			StringTokenizer st = null;
			for(int j = 0; j < m; j++) {
				if(j % 10 == 0) st = new StringTokenizer(br.readLine()); // 10개 마다 새로운 줄에 입력 받기
				
				// 입력 받은 수를 양쪽 큐에 번갈아 가면서 삽입
				if(pq1.size() == pq2.size()) {
					pq2.offer(Integer.parseInt(st.nextToken()));
				}
				else pq1.offer(Integer.parseInt(st.nextToken()));
				
				// pq1에서 가장 큰 수가 pq2의 가장 작은 수보다 크다면
				// 둘이 교환해서 pq1에는 작은 수만 pq2에는 큰 수만 있도록 유지
				if(!pq1.isEmpty() && !pq2.isEmpty() && pq1.peek() > pq2.peek()) {
					int max = pq1.poll();
					int min = pq2.poll();
					pq1.offer(min);
					pq2.offer(max);
				}
				
				// 홀수번째 수를 읽을때마다 중간값 결과에 추가
				if(j % 2 == 0) {
					cnt++;
					result.append(pq2.peek()).append(" ");
				}
			}
			
			System.out.println(cnt);
			System.out.println(result);
		}
	}
}