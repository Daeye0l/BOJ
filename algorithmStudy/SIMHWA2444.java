package algorithmStudy;

import java.io.*;

public class SIMHWA2444 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= n; i++) {
			for(int j = n; j > i; j--) {
				System.out.print(" ");
			}
			for(int k = 0; k < 2 * i - 1; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for(int i = n-1; i >= 1; i--) {
			for(int j = n; j > i; j--) {
				System.out.print(" ");
			}
			for(int k = 2 * i - 1; k >= 1; k--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}