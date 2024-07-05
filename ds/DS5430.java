package ds;

import java.io.*;
import java.util.*;

public class DS5430 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			Deque<Integer> deq = new LinkedList<>();
			boolean isReverse = false; // 배열이 뒤집혔는지 판별
			boolean isError = false; // 에러가 생겼는지 판별
			
			String func = br.readLine();
			int n = Integer.parseInt(br.readLine());
			String arr = br.readLine();
			
			if(n > 0) {
				// 괄호부분을 빼고 ,로 파싱해서 데크에 추가
				for(String s : arr.substring(1, arr.length()-1).split(",")) {
					deq.addLast(Integer.parseInt(s));
				}
			}
			
			// 배열이 뒤집혔으면 뒤에서 연산, 아니라면 앞에서 연산
			for(int j = 0; j < func.length(); j++) {
				if(func.charAt(j) == 'R') {
					isReverse = !isReverse;
				}
				else {
					if(deq.isEmpty()) {
						isError = true;
						break;
					}
					if(isReverse) {
						deq.removeLast();
					}
					else {
						deq.removeFirst();
					}
				}
			}
			
			if(isError) {
				System.out.println("error");
			}
			else {
				StringBuilder sb = new StringBuilder();
				sb.append('[');
				while(!deq.isEmpty()) {
					if(isReverse) {
						sb.append(deq.removeLast());
					}
					else {
						sb.append(deq.removeFirst());
					}
					// 데크에 남은 요소가 있다면 ,추가
					if(!deq.isEmpty()) {
						sb.append(',');
					}
				}
				sb.append(']');
				System.out.println(sb);
			}
		}
	}
}