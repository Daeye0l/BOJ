package ds;

import java.io.*;
import java.util.*;

public class DS20920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		List<String> words = new ArrayList<>();
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			String word = br.readLine();
			// m길이 이상인 단어만 대상으로 함
			if(word.length() >= m) {
				// map에 단어가 없으면 추가하고 있다면 빈도만 하나 증가
				if(!map.containsKey(word)) {
					map.put(word, 1);
					words.add(word);
				}
				else {
					map.put(word, map.get(word)+1);
				}
			}
		}
		
		// 리스트를 우선순위에 맞게 정렬
		Collections.sort(words, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(map.get(s1) != map.get(s2)) {
					return map.get(s2) - map.get(s1);
				}
				if(s1.length() != s2.length()) {
					return s2.length() - s1.length();
				}
				return s1.compareTo(s2);
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for(String word : words) {
			sb.append(word).append("\n");
		}
		
		System.out.println(sb);
	}
}