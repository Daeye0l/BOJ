package math;

import java.io.*;

public class M2577 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());
		
		String result = Integer.toString(A * B * C);
		
		int[] count = new int[10];
		for(int i = 0 ; i < result.length(); i++) {
			count[Character.getNumericValue(result.charAt(i))]++;
		}
		
		for(int i : count) {
			System.out.println(i);
		}
	}
}