package ds;

import java.io.*;
import java.util.*;

public class DS28279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> deq = new LinkedList<>();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken();
			if(st.hasMoreTokens()) {
				int num = Integer.parseInt(st.nextToken());
				if(command.equals("1")) deq.addFirst(num);
				else deq.addLast(num);
			}
			else {
				if(command.equals("3")) {
					if(!deq.isEmpty()) System.out.println(deq.pollFirst());
					else System.out.println(-1);
				}
				else if(command.equals("4")) {
					if(!deq.isEmpty()) System.out.println(deq.pollLast());
					else System.out.println(-1);
				}
				else if(command.equals("5")) {
					System.out.println(deq.size());
				}
				else if(command.equals("6")) {
					if(deq.isEmpty()) System.out.println(1);
					else System.out.println(0);
				}
				else if(command.equals("7")) {
					if(!deq.isEmpty()) System.out.println(deq.peekFirst());
					else System.out.println(-1);
				}
				else {
					if(!deq.isEmpty()) System.out.println(deq.peekLast());
					else System.out.println(-1);
				}
			}
		}
	}
}