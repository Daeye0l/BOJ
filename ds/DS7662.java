package ds;

import java.io.*;
import java.util.*;

public class DS7662 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			PriorityQueue<Integer> ascending = new PriorityQueue<>(); // 오름차순 큐
			PriorityQueue<Integer> decending = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 큐
			HashMap<Integer, Integer> cntMap = new HashMap<>();
			int total = 0; // 원소의 총 갯수

			int k = Integer.parseInt(br.readLine());
			for (int j = 0; j < k; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String command = st.nextToken(); // 연산자
				int value = Integer.parseInt(st.nextToken()); // 값
				// 삽입 연산인 경우
				if (command.equals("I")) {
					ascending.offer(value);
					decending.offer(value);
					if(!cntMap.containsKey(value)) cntMap.put(value, 1);
					else cntMap.put(value, cntMap.get(value)+1);
					total++;
				}
				// 삭제 연산인 경우
				else {
					if (value == 1) { // 최댓값 삭제
						while (!decending.isEmpty()) {
							int del = decending.poll();
							int cnt = cntMap.get(del);
							if (cnt > 0) {
								cntMap.put(del, cnt-1);
								total--;
								break;
							}
						}
					} 
					else { // 최솟값 삭제
						while (!ascending.isEmpty()) {
							int del = ascending.poll();
							int cnt = cntMap.get(del);
							if (cnt > 0) {
								cntMap.put(del, cnt-1);
								total--;
								break;
							}
						}
					}
				}
			}
			
			if (total == 0) sb.append("EMPTY").append("\n");
			else {
				int max = 0;
				int min = 0;
				while(!decending.isEmpty()) {
					max = decending.poll();
					if(cntMap.get(max) > 0) break;
				}
				while(!ascending.isEmpty()) {
					min = ascending.poll();
					if(cntMap.get(min) > 0) break;
				}
				sb.append(max).append(" ").append(min).append("\n");
			}
		}
		System.out.println(sb);
	}
}