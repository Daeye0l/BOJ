package ds;

import java.io.*;
import java.util.*;

public class DS10799 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> stack = new Stack<>();
		String s = br.readLine(); // 문자열 입력받기
		int result = 0; // 잘려진 쇠막대기의 총 개수
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i); // 여는 괄호 or 닫는 괄호
			if(c == '(') { // 여는 괄호인 경우
				stack.push(c); // 스택에 집어 넣는다.
			}
			else { // 닫는 괄호인 경우
				if(s.charAt(i-1) == '(') { // 이전 문자가 '('라면 레이저가 완성된 경우
					stack.pop();
					result += stack.size();
				}
				else { // 이전 문자가 ')라면 결과값 +1을 해준다.
					stack.pop();
					result++;
				}
			}
		}
		
		System.out.println(result);
	}
}