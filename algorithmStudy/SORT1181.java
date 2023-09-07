package algorithmStudy;

import java.io.*;
import java.util.*;

public class SORT1181 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 단어의 개수 입력받기
		String[] word = new String[n]; // 단어를 저장할 배열 생성

		// 단어를 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			word[i] = br.readLine();
		}

		Arrays.sort(word, new Comparator<String>() {
			public int compare(String s1, String s2) {
				if(s1.length() == s2.length()) { // 길이가 같다면 사전순으로 정렬
					return s1.compareTo(s2);
				}
				else {
					return s1.length() - s2.length(); // 길이순으로 정렬
				}
			}
		});
		
		// 길이, 사전순으로 정렬된 상태에서 중복되는 단어는 제외하고 출력
		System.out.println(word[0]);
		for(int i = 1; i < n; i++) {
			if(word[i].equals(word[i-1])) {
				continue;
			}
			else {
				System.out.println(word[i]);	
			}
		}
	}
}
