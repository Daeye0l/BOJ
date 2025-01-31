package math;

import java.io.*;
import java.util.*;

public class M10250 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			
			if(n % h != 0) {
				System.out.println((n%h*100) + (n/h+1));
			}
			else {
				System.out.println((h*100) + (n/h));
			}
		}
	}
}