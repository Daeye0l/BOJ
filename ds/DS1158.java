package ds;

import java.io.*;
import java.util.*;

public class DS1158 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // N 입력받기
		int k = Integer.parseInt(st.nextToken()); // K 입력받기
		
		Queue<Integer> q = new LinkedList<>(); // 큐 생성
		
		// 큐에 1 ~ n까지의 정수 집어넣기
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		
		StringBuilder result = new StringBuilder();
		result.append("<");
		while(!q.isEmpty()) { // 큐 안에 있는 모든 수를 제거할 때까지 반복
			for(int i = 1; i < k; i++) {
				q.add(q.remove()); // k-1번은 큐의 앞에 있는 원소르 빼서 다시 추가하고 
			}
			
			// k번째의 수는 빼서 결과에 추가
			if(q.size() != 1) {
				result.append(q.remove()).append(", ");
			}
			else {
				result.append(q.remove());
			}
		}
		
		System.out.print(result.append(">"));
	}
}