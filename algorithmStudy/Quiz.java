// 회문 문자열
package algorithmStudy;

import java.io.*;

public class Quiz {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine(); // 문자여 입력받기
		
		int f = 0; // 문자열의 시작 인덱스
		int l = s.length()-1; // 문자열의 마지막 인덱스
		boolean palindrome = true; // 회문 판별 변수
		while(f < l) {
			// 두 문자가 대소문자를 구분하지 않고 같은 경우
			if(s.charAt(f) == s.charAt(l) || Math.abs(s.charAt(f)-s.charAt(l)) == 32) {
				f++; l--;
			}
			else {
				palindrome = false; // 두 문자가 대소문자를 구분하지 않고 같지 않다면 회문이 아님
				f++; l--;
			}
		}
		
		if(palindrome == true) System.out.print("YES");
		else System.out.print("NO");
	}
}