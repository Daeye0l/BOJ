package implementation;

import java.io.*;
import java.util.*;

public class IMPL30804 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] fruit = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			fruit[i] = Integer.parseInt(st.nextToken());
			
		}
		
		HashMap<Integer, Integer> map = new HashMap<>(); // 과일의 종류와 갯수를 매핑
		int left = 0;
		int right = 0;
		int max = 0;
		while(right < n) { // right 포인터가 과일 배열 범위를 벗어나면 종료
			if(!map.containsKey(fruit[right])) {
				map.put(fruit[right], 1);
			}
			else map.put(fruit[right], map.get(fruit[right])+1);
			
			while(map.size() > 2) { // 과일이 2종류를 초과한 경우
				map.put(fruit[left], map.get(fruit[left])-1); // 앞에서부터 과일을 제거
				if(map.get(fruit[left]) == 0) {
					map.remove(fruit[left]);
				}
				left++;
			}
			
			max = Math.max(max, right-left+1); // 최대 길이 갱신
			right++; // right 포인터 증가
		}
		
		System.out.println(max);
	}
}