package ds;

import java.io.*;
import java.util.*;

public class DS2346 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Deque<int[]> deq = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			int[] arr = {i, num};
			deq.addLast(arr);
		}
		
		while(!deq.isEmpty()) {
			int[] remove = deq.removeFirst();
			sb.append(remove[0]).append(" ");
			
			if(deq.isEmpty()) break;
			
			if(remove[1] > 0) {
				for(int i = 0; i < remove[1]-1; i++) {
					deq.addLast(deq.removeFirst());
				}
			}
			else if(remove[1] < 0){
				for(int i = 0; i < Math.abs(remove[1]); i++) {
					deq.addFirst(deq.removeLast());
				}
			}
		}
		
		System.out.println(sb);
	}
}