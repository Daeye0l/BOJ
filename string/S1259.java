package string;

import java.io.*;

public class S1259 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s = br.readLine();
			boolean check = true;
			
			if(s.equals("0")) { // 0을 입력받은 경우
				break; // 종료
			}
			else {
				int f = 0; // 문자열의 첫 번째 인덱스
				int l = s.length() - 1; // 문자열의 마지막 인덱스
				
				while(f <= l) {
					if(s.charAt(f) == s.charAt(l)) { // 첫 번째 값과 마지막 값이 같다면 
						f++; // 첫 번째 인덱스를 1 증가시키고
						l--; // 마지막 인덱스 1 감소시킴
					}
					else { // 회문이 아닌 경우
						check = false;
						System.out.println("no");
						break;
					}
				}
				
				if(check == true) System.out.println("yes");
			}
		}
	}
}