package ds;

import java.io.*;
import java.util.*;

public class DS2164 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // N 입력받기
		Queue<Integer> q = new LinkedList<>(); // 큐 생성
		
		// 큐에 1 ~ N까지 집어넣기
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		
		// 큐에 마지막 하나가 남을 때까지 반복
		while(q.size() != 1) {
			q.remove(); // 큐에서 하나를 제거하고
			// 다음 하나를 빼서 뒤로 넘김
			int j = q.remove();
			q.add(j);
		}
		
		System.out.print(q.remove());
	}
}