package math;

import java.io.*;
import java.util.*;

public class M30802 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] size = new int[6];
		int shirtCnt = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < size.length; i++) {
			size[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int p = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < size.length; i++) {
			if(size[i] % t == 0) {
				shirtCnt += size[i]/t;
			}
			else shirtCnt += size[i]/t + 1;
		}
		
		System.out.println(shirtCnt);
		System.out.println(n/p + " " + n%p);
	}
}