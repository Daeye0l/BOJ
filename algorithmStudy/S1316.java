package algorithmStudy;

import java.io.*;

public class S1316 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 단어의 개수
		int cnt = 0; // 그룹 단어의 개수
		
		// 단어의 개수만큼 반복
		for(int i = 0; i < n; i++) {
			String s = br.readLine(); // 단어 입력받기
			String tmp = Character.toString(s.charAt(0)); // tmp를 단어의 첫 문자로 지정
			boolean group = true;
			
			for(int j = 1; j < s.length(); j++) {
				if(s.charAt(j-1) == s.charAt(j)) { // 단어의 문자와 그 다음 문자가 같은 경우
					tmp += s.charAt(j);
				}
				else { // 단어의 문자와 그 다음 문자가 다른 경우
					if(tmp.contains(Character.toString(s.charAt(j)))){ // 문자가 tmp에 이미 있는 경우
						group = false; // 그룹이 아님
						break;
					}
					else { // 문자가 tmp에 없는 문자인 경우
						tmp += s.charAt(j);
					}
				}
			}
			
			if(group == true) {
				cnt += 1;
			}
		}
	
		System.out.print(cnt);
	}
}