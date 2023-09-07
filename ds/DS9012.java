package ds;

import java.io.*;
import java.util.*;

public class DS9012 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력받기
		
		Stack<Character> stack = new Stack<>(); // 괄호를 집어넣을 스택 생성
		
		for(int i = 0; i < t ; i++) {
			String s = br.readLine(); // 괄호 문자열 입력받기 
			
			for(int j = 0; j < s.length(); j++) { // 문자열의 문자들을 반복하면서
				char c = s.charAt(j);
				if(c == '(') { // 여는 괄호인 경우
					stack.push(c); // 스택에 집어넣고
				}
				else { // 닫는 괄호일 때
					if(stack.isEmpty()) { // 스택이 비었으면
						stack.push(c); // 닫는 괄호를 넣고
						break; // 종료 -> 이미 VPS가 아니기 때문
					}
					else { // 스택에 여는 괄호가 있는 경우
						stack.pop();
					}
				}
			}
			
			if(stack.isEmpty()) { // 스택이 비어있으면 YES 출력
				System.out.println("YES");
			} 
			else { // 스택에 어떤 종류의 괄호라도 남아 있으면 NO 출력
				System.out.println("NO");
			}
			
			stack.clear();
		}
	}
}