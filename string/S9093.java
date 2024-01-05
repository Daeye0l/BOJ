package string;

import java.io.*;
import java.util.*;

public class S9093 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 문장 입력받기
			StringBuilder sb = new StringBuilder();
			
			while(st.hasMoreTokens()) { // 단어가 있다면 뒤집어서 추가
				sb.append(reverse(st.nextToken()) + " ");
			}
			
			System.out.println(sb); // 출력
		}
	}
	
	private static String reverse(String word) {
		String result = ""; // 결과 문자열		
		for(int i = word.length()-1; i >= 0; i--) { // 입력으로 들어온 문자열의 뒷 문자부터 결과에 추가
			result += word.charAt(i);
		}
		return result; // 반환
	}
}