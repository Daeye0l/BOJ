package string;

import java.io.*;

public class S10808 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] counts = new int[26]; // 각 알파벳의 개수를 저장할 배열
		
		// 문자열의 각 문자에 해당하는 배열 위치의 카운트를 증가
		String S = br.readLine();
		for(int i = 0; i < S.length(); i++) {
			counts[S.charAt(i)-97]++;
		}
		
		// 출력
		for(int i : counts) {
			System.out.print(i + " ");
		}
	}
}
