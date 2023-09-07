package ds;

import java.io.*;
import java.util.*;

public class DS10866 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 명령의 개수 입력받기
		Deque<Integer> deq = new LinkedList<>(); // 덱 생성

		for(int i = 0; i < n; i++) { // 명령 수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken(); // 명령

			if(st.hasMoreTokens()) { // push 명령인 경우
				if(command.equals("push_front")) deq.addFirst(Integer.parseInt(st.nextToken()));
				else deq.addLast(Integer.parseInt(st.nextToken()));
			}
			else { // 나머지 명령인 경우
				if(command.equals("pop_front")) {
					if(!deq.isEmpty()) System.out.println(deq.removeFirst());
					else System.out.println(-1);
				}
				else if(command.equals("pop_back")) {
					if(!deq.isEmpty()) System.out.println(deq.removeLast());
					else System.out.println(-1);
				}
				else if(command.equals("size")) System.out.println(deq.size());
				else if(command.equals("empty")) {
					if(deq.isEmpty()) System.out.println(1);
					else System.out.println(0);
				}
				else if(command.equals("front")) {
					if(!deq.isEmpty()) System.out.println(deq.peekFirst());
					else System.out.println(-1);
				}
				else {
					if(!deq.isEmpty()) System.out.println(deq.peekLast());
					else System.out.println(-1);
				}
			}
		}
	}
}