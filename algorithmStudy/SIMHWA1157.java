package algorithmStudy;

import java.io.*;

public class SIMHWA1157 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		String s = br.readLine(); // 단어 입력받기
		
		int[] arr = new int[26]; // 각 알파벳의 개수를 저장할 배열 생성
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) >= 65 && s.charAt(i) <= 90) { // 대문자인 경우
				arr[s.charAt(i) - 65]+= 1;
			}
			else { // 소문자인 경우
				arr[s.charAt(i) - 97]+= 1;
			}
		}
		
		int max = 0;
		char c = '?';
		
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i]; // 가장 많이 사용된 알파벳 갱신
				c = (char)(i + 65);
			}
			else if(arr[i] == max) { // 가장 많이 사용된 알파벳이 더 있는 경우 c 갱신
				c = '?';
			}
		}
		
		System.out.print(c);
	}
}