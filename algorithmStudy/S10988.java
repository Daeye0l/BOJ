package algorithmStudy;

import java.io.*;

public class S10988 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine(); // 첫째 줄에 단어 입력받기
		int p = 1; // 팰린드롬 변수
		
		int j = s.length()-1;
		// 단어의 첫 문자와 마지막 문자부터 비교하면서 확인 
		for(int i = 0; i < s.length()/2; i++) {
			if(s.charAt(i) != s.charAt(j)){ // 팰린드롬이 아닌경우
				p = 0;
			}
			j -= 1;
		}
		
		System.out.print(p);
	}
}