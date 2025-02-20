package math;

import java.io.*;
import java.util.*;

public class M9375 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			
			for(int j = 0; j < n; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String category = st.nextToken();
				// 같은 이름을 가진 의상은 존재하지 않으므로 종류별 갯수로 관리
				if(!map.containsKey(category)) {
					map.put(category, 1);
				}
				else map.put(category, map.get(category)+1);
			}
			
			int cases = 1; // 전체 경우의 수
			for(int cnt : map.values()) {
				cases *= (cnt+1); // 각 종류의 의상 갯수에 1을 더해서 조합(아무것도 선택하지 않는 경우까지 포함해서)
			}
			
			sb.append(cases-1).append("\n"); // 전체 경우의 수에서 모든 카테고리에서 아무것도 선택하지 않는 경우를 뺌
		}
		
		System.out.println(sb);
	}
}