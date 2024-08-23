package ds;

import java.io.*;
import java.util.*;

public class DS2504 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> stack = new Stack<>();
		int weight = 1; // 가중치 저장
		int result = 0; // 결과값 저장
		boolean valid = true; // 올바른 괄호열인지 판별
		
		String s = br.readLine();
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// 여는 괄호에 대해 정상적인 쌍을 이룬다면 안에 있는 값들은 분배 법칙에 의해 모두 *3 또는 *2의 값을 가질것임
			if(c == '[') {
				stack.push(c);
				weight *= 3;
			}
			else if(c == '(') {
				stack.push(c);
				weight *= 2;
			}
			// 닫는 괄호가 나왔을 때
			else if(c == ']') {
				if(!stack.isEmpty() && stack.peek() == '[') { // 단일 괄호가 완성
					if(s.charAt(i-1) == '[') { // 중복 계산을 방지하기 위해 단일 괄호에 대해서만 결과값 누적
						result += weight;	
					}
				}
				else { // 올바르지 않은 괄호열인 경우
					valid = false;
					break;
				}
				stack.pop();
				weight /= 3;
			}
			else if(c == ')') {
				if(!stack.isEmpty() && stack.peek() == '(') {
					if(s.charAt(i-1) == '(') {
						result += weight;	
					}
				}
				else {
					valid = false;
					break;
				}
				stack.pop();
				weight /= 2;
			}
		}
		
		if(stack.isEmpty() && valid) {
			System.out.println(result);	
		}
		else {
			System.out.println(0);
		}
	}
}