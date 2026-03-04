package math;

import java.io.*;
import java.util.*;

public class M3273 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int x = Integer.parseInt(br.readLine());
		
		int left = 0;
		int right = arr.length-1;
		int result = 0;
		while(left < right) {
			if(arr[left] + arr[right] == x) {
				result++;
				left++;
				right--;
			}
			else if(arr[left] + arr[right] < x) {
				left++;
			}
			else right--;
		}
		
		System.out.println(result);
	}
}