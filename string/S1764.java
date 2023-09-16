package string;

import java.io.*;
import java.util.*;

public class S1764 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 듣도 못한 사람 수
		int m = Integer.parseInt(st.nextToken()); // 보도 못한 사람 수
		
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<String> arr = new ArrayList<>();
		
		// 듣도 못한 사람 맵에 추가(key: 이름, value: 0)
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			if(!map.containsKey(s)) map.put(s, 0);
		}
		
		for(int i = 0; i < m; i++) {
			String s = br.readLine();
			if(map.containsKey(s)) map.put(s, map.get(s) + 1); // 듣도 보도 못한 사람은 값을 1로 변경
			else map.put(s, 0);
			if(map.get(s) == 1) arr.add(s); // 듣도 보도 못한 사람은 리스트에 추가 
		}
		
		Collections.sort(arr); // 리스트를 사전순으로 정렬
		System.out.println(arr.size());
		for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
	}
}