package recursion;

import java.io.*;

public class RECUR25501 {
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			String s = br.readLine();
			cnt = 0;
			System.out.print(isPalindrome(s.toCharArray()) + " ");
			System.out.println(cnt);
		}
	}
	
	private static int recursion(char[] s, int l, int r) {
		cnt++;
		if(l >= r) return 1;
		else if(s[l] != s[r]) return 0;
		else return recursion(s, l+1, r-1);
	}
	
	private static int isPalindrome(char[] s) {
		return recursion(s, 0, s.length-1);
	}
}