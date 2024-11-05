package string;

import java.io.*;
import java.util.*;

public class S12904 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String t = br.readLine();
		Deque<Character> deq = new LinkedList<>();
		
		// t에서 s로 변경하기 위해 데크에 t문자열을 순서대로 삽입
		for(int i = 0; i < t.length(); i++) {
			deq.offer(t.charAt(i));
		}
		
		StringBuilder result = new StringBuilder();
		while(!deq.isEmpty()) {
			// s와 t의 길이가 s의 길이보다 긴 경우
			// s.length() != deq.size()로 해서 계속 틀림
			if(s.length() < deq.size()) {
				// t의 마지막 문자가 A인 경우 그냥 제거
				if(deq.peekLast() == 'A') {
					deq.pollLast();
				}
				// t의 마지막 문자가 B인 경우
				else {
					// 제거 후 문자열 뒤집기
					deq.pollLast();
					Stack<Character> stack = new Stack<>();
					while(!deq.isEmpty()) {
						stack.push(deq.poll());
					}
					while(!stack.isEmpty()) {
						deq.offer(stack.pop());
					}
				}
			}
			// s와 t의 길이가 같아지면 결과 문자열 생성
			else {
				result.append(deq.poll());
			}
		}
		
		if(!s.equals(result.toString())) System.out.println(0);
		else System.out.println(1);
	}
}