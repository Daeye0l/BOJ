package algorithmStudy;

import java.io.*;

public class S10809 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine(); // 문자열 입력받기
		String alphabet = "abcdefghijklmnopqrstuvwxyz"; // 알파벳 문자열 생성
		int[] result = new int[alphabet.length()]; // 위치를 저장할 배열
		boolean[] changed = new boolean[alphabet.length()];
		
		for(int i = 0; i < alphabet.length(); i++) { // 각 알파벳을 반복하면서 
			if(s.contains(Character.toString(alphabet.charAt(i)))) { // 알파벳이 단어에 있는 경우
				// 문자열에서 해당 알파벳의 위치를 결과 배열에 저장
				for(int j = 0; j < s.length(); j++) {
					if(alphabet.charAt(i) == s.charAt(j) && changed[i] == false) {
						result[i] = j;
						changed[i] = true;
					}
				}
			}
			else result[i] = -1; // 알파벳이 단어에 없는 경우
		}
		
		for(int i = 0; i < result.length; i++) {
			if(i == result.length - 1) System.out.print(result[i]);
			else System.out.print(result[i] + " ");
		}
	}
}