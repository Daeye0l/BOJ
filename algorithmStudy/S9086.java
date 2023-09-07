package algorithmStudy;

import java.io.*;

public class S9086 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 테스트 횟수
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine(); // 문자열 입력받기
			StringBuilder sb = new StringBuilder();
			
			int f = 0; // 문자열의 첫 번째 인덱스
			int l = s.length() - 1; // 문자열의 마지막 인덱스
			
			// StringBuilder에 첫 번째 문자와 마지막 문자 추가하기
			sb.append(s.charAt(f));
			sb.append(s.charAt(l));
			
			System.out.println(sb);
		}
	}
}