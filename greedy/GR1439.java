package greedy;

import java.io.*;

public class GR1439 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine(); // 문자열 입력받기
		
		int part_one = 0; // 연속적으로 1이 등장하는 부분의 수를 저장할 변수
		int part_zero = 0; // 연속적으로 0이 등장하는 부분의 수를 저장할 변수
		
		if(s.charAt(0) == '1') { // 시작 숫자가 1이라면
			part_one++; // 1 부분수열 개수 증가
		}
		else part_zero++; // 0 부분수열 개수 증가
		
		for(int i = 1; i < s.length(); i++) {
			if(s.charAt(i) != s.charAt(i-1)) { // 수열이 진행되다가 숫자가 바뀌었을 때
				if(s.charAt(i) == '1') { // 바뀐 숫자가 1인 경우
					part_one++; // 1인 부분수열 개수 증가
				}
				else { // 바뀐 숫자가 0인 경우
					part_zero++; // 0인 부분수열 개수 증가
				}
			}
		}
		
		System.out.print(Math.min(part_one, part_zero));
	}
}