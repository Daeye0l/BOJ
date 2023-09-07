package algorithmStudy;

import java.io.*;

public class Test {

	public static void main(String[] args) throws IOException {
		// 특정 문자 뒤집기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		
		char[] arr = new char[s.length()];
		for(int i = 0; i < s.length(); i++) {
			arr[i] = s.charAt(i);
		}
		
		int f = 0;
		int l = s.length()-1;
		while(f < l) {
			if(!Character.isAlphabetic(arr[f])) {
				f++;
			}
			if(!Character.isAlphabetic(arr[l])) {
				l--;
			}
			if(Character.isAlphabetic(arr[f]) && Character.isAlphabetic(arr[l])) {
				char tmp = arr[f];
				arr[f] = arr[l];
				arr[l] = tmp;
				f++;
				l--;
			}
		}
		
		String result = "";
		for(int i = 0; i < s.length(); i++) {
			result += arr[i];
		}
		
		System.out.print(result);
		
		/* 중복문자 제거
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine(); // 문자열 입력받기
		String result = Character.toString(s.charAt(0)); // 결과를 저장할 문자열
		
		for(int i = 1; i < s.length(); i++) { // 두번째 문자부터 마지막 문자까지 반복
			if(result.contains(Character.toString(s.charAt(i)))) { // 결과 문자열에 해당 문자가 이미 있는 경우
				continue;
			}
			else { // 결과 문자열에 해당 문자가 없는 경우
				result += s.charAt(i); // 문자열에 문자를 결합
			}
		}
		
		System.out.print(result); */
	}
}