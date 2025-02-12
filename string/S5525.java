package string;

import java.io.*;

public class S5525 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		char[] s = new char[m];
		String input = br.readLine();
		
		// 문자열 s를 배열에 저장
		for(int i = 0; i < input.length(); i++) {
			s[i] = input.charAt(i);
		}
		
		int result = 0; // PN 패턴의 갯수
		int i = 0;
		while(i < input.length()) {
			// 문자열에서 I를 나온 경우
			if(s[i] == 'I') {
				int cnt = 0; // O가 1개인 IOI 패턴의 갯수
				while(i+1 < s.length && i+2 < s.length &&
					  s[i+1] == 'O' && s[i+2] == 'I') {
					cnt++; // IOI 패턴의 갯수 증가
					i += 2; // 인덱스 이동
					if(cnt == n) { // PN 패턴이 완성된 경우
						result++; // 결과값 증가
						cnt--; // 맨 앞의 IOI 패턴을 제외하고 계속 찾기
					}
				}
			}
			i++;
		}
		
		System.out.println(result);
	}
}