package ds;

import java.io.*;
import java.util.*;

public class DS4949 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine(); // 문자열 입력받기
			Stack<Character> stack = new Stack<>(); // 스택 선언
			boolean balance = true; // 문자열이 균형을 이루는지 판별할 변수
			
			if(s.equals(".")) return; // .을 입력 받는다면 종료
			int i = 0;
			while(s.charAt(i) != '.') { // .이 아닌 동안 반복
				if(s.charAt(i) == '(' || s.charAt(i) == '[') {
					stack.push(s.charAt(i));
				}
				else if(s.charAt(i) == ')') {
					if(!stack.isEmpty() && stack.peek() == '(') stack.pop();
					// ')'를 입력 받았을 때 스택이 비어있거나 스택의 가장 위에 있는 값이 '('가 아니라면 균형이 깨짐
					else {
						balance = false;
						break;
					}
				}
				else if(s.charAt(i) == ']') {
					if(!stack.isEmpty() && stack.peek() == '[') stack.pop();
					// ']'를 입력 받았을 때 스택이 비어있거나 스택의 가장 위에 있는 값이 '['가 아니라면 균형이 깨짐
					else {
						balance = false;
						break;
					}
				}
				i++;
			}
			
			// 스택이 비어있고(여는 괄호들만 들어왔을 경우에 대비) 균형이 맞다면 yes 출력
			if(stack.isEmpty() && balance) System.out.println("yes");
			else System.out.println("no");
		}
	}
}