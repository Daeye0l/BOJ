package ds;

import java.io.*;
import java.util.*;

public class DS10815 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] n = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			n[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine());
		int[] m = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(n);
		
		for(int i = 0; i < M; i++) {
			if(binary_search(m[i], n)) {
				System.out.print(1 + " ");
			}
			else System.out.print(0 + " ");
		}
	}
	
	private static boolean binary_search(int key, int[] arr) {
		int low = 0;
		int high = arr.length-1;
		int mid;
		
		while(low <= high) {
			mid = (low + high) / 2;
			
			if(key == arr[mid]) {
				return true;
			}
			else if(key < arr[mid]) {
				high = mid - 1;
			}
			else {
				low = mid + 1;
			}
		}
		return false;
	}
}