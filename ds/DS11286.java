package ds;

import java.io.*;
import java.util.*;

public class DS11286 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> heap = new PriorityQueue<>(new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				if(Math.abs(a) == Math.abs(b)) { // 두 수의 절댓값이 같은 경우
					return a - b;
				}
				else return Math.abs(a) - Math.abs(b); // 나머지 경우
			}
		});
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			int x = Integer.parseInt(br.readLine());
			if(x != 0) { // 0이 아니라면 힙에 추가
				heap.add(x);
			}
			else {
				if(heap.isEmpty()) { // 0이면서 힙이 비어있는 경우
					sb.append(0 + "\n");
				}
				else {
					sb.append(heap.remove() + "\n");
				}
			}
		}
		
		System.out.println(sb);
	}
}