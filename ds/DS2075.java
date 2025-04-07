package ds;

import java.io.*;
import java.util.*;

public class DS2075 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> que = new PriorityQueue<>(); // 오름차순 우선순위 큐
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				// 큐에 삽입하고 큐의 원소가 n개를 초과하면 가장 작은 값 제거 
				que.offer(Integer.parseInt(st.nextToken()));
				if(que.size() > n) {
					que.poll();
				}
			}
		}
		
		System.out.println(que.poll()); // n번째 큰 수 출력
	}
}