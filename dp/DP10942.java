package dp;

import java.io.*;
import java.util.*;

public class DP10942 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(isPalindrome(arr, s, e)) result.append(1);
			else result.append(0);
			result.append("\n");
		}
		
		System.out.println(result);
	}
	
	private static boolean isPalindrome(int[] arr, int s, int e) {
		int l = s;
		int r = e;
		while(l < r) {
			if(arr[l] == arr[r]) {
				l++;
				r--;
			}
			else return false;
		}
		return true;
	}
}