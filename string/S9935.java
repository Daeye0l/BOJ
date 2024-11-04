package string;

import java.io.*;
import java.util.*;

public class S9935 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String explosion = br.readLine();
		Stack<Character> stack = new Stack<>();
		
		for(int i = 0; i < input.length(); i++) {
			stack.add(input.charAt(i)); // 입력 문자열의 문자를 하나씩 큐에 삽입
			
			// 큐의 크기가 폭발 문자열의 길이 이상일 때부터
			if(stack.size() >= explosion.length()) {
				boolean check = true;
				// 폭발 문자열과 겹치는 구간이 있는지 확인
				for(int j = 0; j < explosion.length(); j++) {
					int k = stack.size() - explosion.length() + j;
					if(stack.get(k) != explosion.charAt(j)) {
						// 폭발 문자열과 조기에 일치하지 않는다면 종료
						check = false;
						break;
					}
				}	
				if(check) {
					// 스택에서 제거
					for(int j = 0; j < explosion.length(); j++) {
						stack.pop();	
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.println(sb);
		}
	}
}