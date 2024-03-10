package greedy;

import java.io.*;
import java.util.*;

public class GR1449 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 물이 새는 곳의 개수
		int l = Integer.parseInt(st.nextToken()); // 가지고 있는 테이프의 길이
		int[] locations = new int[n]; // 물이 새는 곳의 위치를 저장할 배열
		int cnt = 1; // 필요한 테이프의 개수
		
		// 물이 새는 곳의 위치를 입력 받아 저장하고 오름차순으로 정렬
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			locations[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(locations);
		
		int start = 0;
		for(int end = 1; end < n; end++) {
			if(locations[end] - locations[start] + 1 <= l) {
				continue;
			}
			else {
				cnt++;
				start = end;
			}
		}
		
		System.out.println(cnt);	
	}
}