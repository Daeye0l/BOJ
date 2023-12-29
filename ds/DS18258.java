package ds;

import java.io.*;
import java.util.*;

public class DS18258 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine()); // 명령 개수 입력 받기
		
		Deque<String> deque = new LinkedList<>(); // 큐 선언
		
		for(int i = 0; i < N; i++) { // 명령의 개수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken(); // 명령
			
			if(st.hasMoreTokens()) { // push 명령이라면
				deque.push(st.nextToken()); // 큐에 정수 집어넣기 
			}
			else {
				if(command.equals("pop")) { // pop인 경우
					if(deque.isEmpty()) bw.write("-1\n");
					else bw.write(deque.removeLast() + '\n');
				}
				else if(command.equals("size")) { // size인 경우
					bw.write(Integer.toString(deque.size()) + '\n');
				}
				else if(command.equals("empty")) { // empty인 경우
					if(deque.isEmpty()) bw.write("1\n");
					else bw.write("0\n");
				}
				else if(command.equals("front")) { // front인 경우
					if(deque.isEmpty()) bw.write("-1\n");
					else bw.write(deque.getLast() + '\n');
				}
				else { // back인 경우
					if(deque.isEmpty()) bw.write("-1\n");
					else bw.write(deque.getFirst() + '\n');
				}
			}
		}
		bw.flush();
		bw.close();
	}
}