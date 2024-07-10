package ds;

import java.io.*;
import java.util.*;

public class DS2493 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] height = new int[n];
		Stack<Integer> stack = new Stack<>();
		List<Integer> result = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		// 17298문제와 거의 동일
		for(int i = 0; i < n; i++) {
			while(!stack.isEmpty() && height[stack.peek()] < height[i]) stack.pop();
			if(stack.isEmpty()) {
				result.add(0);
			}
			else {
				result.add(stack.peek() + 1);
			}
			stack.push(i);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(result.get(i)).append(" ");
		}
		System.out.println(sb);
	}
}