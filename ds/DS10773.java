package ds;

import java.io.*;
import java.util.*;

public class DS10773 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine()); // K 입력받기
		Stack<Integer> stack = new Stack<>(); // 스택 생성
		
		int j = 0;
		for(int i = 0; i < k; i++) {
			j = Integer.parseInt(br.readLine()); // 각 줄마다 수를 입력받음
			
			if(j != 0) { // 입력받은 수가 0이 아니라면 스택에 넣음
				stack.push(j);
			}
			/* 입력받은 수가 0이라면 최근의 수를 pop함 
			   0을 입력받은 경우 지울 수 있는 수가 있음을 보장하기 때문에
			   스택이 비어있는 경우에 pop하는 경우는 신경쓰지 않는다. */
			else { 
				stack.pop();
			}
		}
		
		// 스택에 남아있는 값을 모두 pop하여 합을 구함
		int sum = 0;
		while(!stack.isEmpty()) {
			sum += stack.pop();
		}
		
		System.out.println(sum);
	}
}