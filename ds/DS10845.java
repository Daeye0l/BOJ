package ds;

import java.io.*;
import java.util.*;

public class DS10845 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 명령의 개수 입력받기
		Deque<Integer> q = new LinkedList<>(); // 자바에서 큐는 LinkedList를 사용하여 생성
		
		for(int i = 0; i < n; i++) { // 명령의 개수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken(); // 명령

			if(st.hasMoreTokens()) { // push 명령인 경우
				q.add(Integer.parseInt(st.nextToken()));
			}
			else {
				// pop인 경우
				if(command.equals("pop")) {
					if(!q.isEmpty()) System.out.println(q.removeFirst());
					else System.out.println(-1);
				}
				// size인 경우
				else if(command.equals("size")) System.out.println(q.size());
				// empty인 경우
				else if(command.equals("empty")) {
					if(q.isEmpty()) System.out.println(1);
					else System.out.println(0);
				}
				// front인 경우
				else if(command.equals("front")) {
					if(!q.isEmpty()) System.out.println(q.getFirst());
					else System.out.println(-1);
				}
				// back인 경우
				else if(command.equals("back")) {
					if(!q.isEmpty()) System.out.println(q.getLast());
					else System.out.println(-1);
				}
			}
		}
	}
}