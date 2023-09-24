package greedy;

import java.io.*;
import java.util.*;

public class GR1715 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 카드 묶음의 개수
		
		PriorityQueue<Integer> q = new PriorityQueue<>(); // 우선순위 큐 생성
		for(int i = 0; i < n; i++) {
			q.add(Integer.parseInt(br.readLine())); // 카드 묶음의 크기를 입력받아 우선순위 큐에 저장
		}
		
		int cnt = 0; // 최소 비교횟수를 저장할 변수
		while(q.size() > 1) { // q의 원소가 2개 이상이라면
			int k = q.remove() + q.remove(); // 크기가 가장 작은 카드 묶음 두개를 제거해서 더해서
			cnt += k; // cnt에 누적하고
			q.add(k); // 더한 값을 다시 큐에 추가
			// 2개를 제거해서 1개를 추가했기 때문에 큐의 원소는 매번 1개씩 줄어든다.
		}
		System.out.print(cnt);
	}
}