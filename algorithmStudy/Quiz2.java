// 유효한 팰린드롬
package algorithmStudy;

import java.io.*;

public class Quiz2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine(); // 문자열 입력받기

		int f = 0; // 문자열 시작 인덱스
		int l = s.length()-1; // 문자열 마지막 인덱스
		boolean palindrome = true; // 회문 판별 변수
		
		while(f < l) {
			// 비교하는 문자에 알파벳이 아닌 문자가 있는 경우
			if(s.charAt(f) >= 33 && s.charAt(f) <= 64 || s.charAt(l) >= 33 && s.charAt(l) <= 64) {
				if(s.charAt(f) >= 33 && s.charAt(f) <= 64) f++;
				if(s.charAt(l) >= 33 && s.charAt(l) <= 64) l--;
			}
			else { // 비교하는 문자에 알파벳만 있는 경우
				// 두 문자가 대소문자를 구분하지 않고 같은 경우
				if(s.charAt(f) == s.charAt(l) || Math.abs(s.charAt(f) - s.charAt(l)) == 32) {
					f++; l--;
				}
				else { // 두 문자가 대소문자를 구분하지 않고 다른 경우
					palindrome = false; // 회문이 아님
					f++; l--;
				}
			}
		}
		
		if(palindrome == true) System.out.print("YES");
		else System.out.print("NO");
	}
}