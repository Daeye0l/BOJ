package ds;

import java.io.*;
import java.util.*;

public class DS28278 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {	
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
		
			if (st.hasMoreTokens()) {
				stack.push(Integer.parseInt(st.nextToken()));
			}
			else {
				if (command.equals("2")) {
					if(!stack.isEmpty()) {
						sb.append(stack.pop()).append("\n");
					}
					else {
						sb.append(-1).append("\n");
					}
				}
				else if (command.equals("3")) {
					sb.append(stack.size()).append("\n");
				}
				else if (command.equals("4")) {
					if(stack.isEmpty()) {
						sb.append(1).append("\n");
					}
					else {
						sb.append(0).append("\n");
					}
				}
				else {
					if(!stack.isEmpty()) {
						sb.append(stack.peek()).append("\n");
					}
					else {
						sb.append(-1).append("\n");
					}
				}
			}
		}
		System.out.print(sb);
	}
}