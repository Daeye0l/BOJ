package ds;

import java.io.*;
import java.util.*;

public class DS10828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 명령의 개수 입력받기
		
		Stack<Integer> stack = new Stack<>(); // 정수형 스택 생성
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken(); // 명령
			
			if(st.hasMoreTokens()) { // push 명령인 경우
				stack.push(Integer.parseInt(st.nextToken()));
			}
			else { // push가 아닌 다른 명령들인 경우
				if(command.equals("top")) { // top인 경우
					if(stack.isEmpty()) {
						System.out.println(-1);
					}
					else {
						System.out.println(stack.peek());	
					}
				}
				else if(command.equals("size")) { // size인 경우
					System.out.println(stack.size());
				}
				else if(command.equals("empty")) { // empty인 경우
					if(stack.isEmpty()) {
						System.out.println(1);
					}
					else {
						System.out.println(0);
					}
				}
				else { // pop인 경우
					if(stack.isEmpty()) {
						System.out.println(-1);
					}
					else {
						System.out.println(stack.pop());
					}
				}
			}
		}
	}
}