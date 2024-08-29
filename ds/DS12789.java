package ds;

import java.io.*;
import java.util.*;

public class DS12789 {
	static Queue<Integer> que;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		que = new LinkedList<>();
		stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			que.add(Integer.parseInt(st.nextToken()));
		}
		
		
		int j = 1;
		while(!que.isEmpty() || !stack.isEmpty()) {
			// 현재 줄 서있는 곳에 있는 경우
			if(position(j).equals("que")) {
				if(que.peek() != j) {
					stack.add(que.poll());
				}
				else {
					que.poll();
					j++;
				}
			}
			// 대기 공간에 있는 경우
			else {
				// 대기 공간 학생들은 다시 줄로 돌아갈 수 없기 때문에 맨 앞이 아니라면 Sad
				if(stack.peek() != j) {
					System.out.println("Sad");
					return;
				}
				else {
					stack.pop();
					j++;
				}
			}
		}
		
		System.out.println("Nice");
	}
	
	// 다음 순번의 학생의 위치를 반환
	private static String position (int n) {
		if(que.contains(n)) {
			return "que";
		}
		else return "stack";
	}
}