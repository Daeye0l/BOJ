package ds;

import java.io.*;
import java.util.*;

public class DS1269 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()); // a 집합의 원소의 개수
		int b = Integer.parseInt(st.nextToken()); // b 집합의 원소의 개수
		int result = 0;
		
		// a 집합의 원소를 입력 받아서 set에 저장
		HashSet<Integer> set_a = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < a; i++) {
			set_a.add(Integer.parseInt(st.nextToken()));
		}
		
		// b 집합의 원소를 입력 받아서 set에 저장
		HashSet<Integer> set_b = new HashSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < b; i++) {
			set_b.add(Integer.parseInt(st.nextToken()));
		}
		
		// A-B의 개수 결과값에 더하기
		for(int i : set_b) {
			if(!set_a.contains(i)) {
				result += 1;
			}
		}
		
		// B-A의 개수 결과값에 더하기
		for(int i : set_a) {
			if(!set_b.contains(i)) {
				result += 1;
			}
		}
		
		System.out.println(result);
		
	}
}