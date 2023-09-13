package ds;

import java.io.*;
import java.util.*;

public class DS11866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N과 K 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		 
		Queue<Integer> q = new LinkedList<>(); // 큐 생성
		
		// 1 ~ N까지 큐에 모두 집어넣기
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		while(!q.isEmpty()) { // 큐에 원소가 없어질 때까지
			// k-1번을 큐에서 빼서 다시 큐에 집어넣고
			for(int i = 1; i < k; i++) {
				q.add(q.remove());
			}
			// k번째 원소를 수열에 추가
			if(q.size() > 1) sb.append(q.remove()).append(", ");
			else sb.append(q.remove()).append(">");
		}
		
		System.out.print(sb);
	}
}