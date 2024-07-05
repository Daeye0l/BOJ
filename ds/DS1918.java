package ds;

import java.io.*;
import java.util.*;

public class DS1918 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Character, Integer> ranks = new HashMap<>();
		ranks.put('*', 3); ranks.put('/', 3);
		ranks.put('+', 2); ranks.put('-', 2);
		ranks.put('(', 1);

		Stack<Character> stack = new Stack<>();
		StringBuilder postfix = new StringBuilder();
		
		String infix = br.readLine();
		for(int i = 0; i < infix.length(); i++) {
			char c = infix.charAt(i);
			// 피연산자인 경우
			if(c >= 'A' && c <= 'Z') postfix.append(c);
			// 여는 괄호인 경우
			else if(c == '(') stack.push(c);
			// 닫는 괄호인 경우
			else if(c == ')') {		
				while(!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				stack.pop(); // 여는 괄호 제거
			}
			// 연산자인 경우
			else {
				// 여는 괄호는 우선순위가 가장 낮기 때문에 닫는 괄호가 나오기 전까지는 스택에서 나올 수 없음 
				while (!stack.isEmpty() && ranks.get(stack.peek()) >= ranks.get(c)) {
                    postfix.append(stack.pop());
                }
				stack.push(c);
			}
		}
		while(!stack.isEmpty()) postfix.append(stack.pop());
		
		System.out.println(postfix);
	}
}