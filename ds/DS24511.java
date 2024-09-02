package ds;

import java.io.*;
import java.util.*;

public class DS24511 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] ds = new int[n];
		Deque<Integer> deq = new LinkedList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			ds[i] = Integer.parseInt(st.nextToken());
		}
		
		// 스택을 거쳐갈 때면 들어간 원소가 그대로 나오기 때문에 큐인 경우만 데크에 추가
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(ds[i] == 0) {
				deq.addLast(num);
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		// 수열의 수가 큐에 들어가고 연산을 반복하면 원소가 오른쪽으로 쉬프트 된다
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			deq.addFirst(num);
			sb.append(deq.removeLast()).append(" ");
		}
		
		System.out.println(sb);
	}
}