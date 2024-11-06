package string;

import java.io.*;

public class S17609 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			if(isPalindrome(s)) {
				System.out.println(0);
				continue;
			}
			
			if(isSimilarPalindrome(s)) {
				System.out.println(1);
				continue;
			}
			else {
				System.out.println(2);
			}
		}
	}
	
	private static boolean isPalindrome(String s) {
		int left = 0;
		int right = s.length()-1;
		while(left < right) {
			// 좌우 양쪽의 문자가 같은 경우 양쪽 인덱스 조정
			if(s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			}
			// 좌우 양쪽의 문자가 다르다면 회문이 아님
			else return false;
		}
		return true;
	}
	
	private static boolean isSimilarPalindrome(String s) {
		int left = 0;
		int right = s.length()-1;
		boolean delete = false;
		while(left < right) {
			// 좌우 양쪽의 문자가 같은 경우 양쪽 인덱스 조정
			if(s.charAt(left) == s.charAt(right)) {
				left++;
				right--;
			}
			// 좌우 양쪽의 문자가 다른 경우
			else {
				// 아직 문자 하나를 지우지 않은 경우
				if(!delete) {
					if(isPalindrome(s.substring(left+1, right+1))) {
						left++;
						delete = true;
					}
					else if(isPalindrome(s.substring(left, right))) {
						right--;
						delete = true;
					}
					else return false;
				}
				// 문자 하나를 이미 지운 경우
				else return false;
			}
		}
		return true;
	}
}