package algorithmStudy;

import java.io.*;

public class GEO10101 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		int c = Integer.parseInt(br.readLine());
		
		if(a == 60 && b == 60 && c == 60) System.out.print("Equilateral");
		else if(a + b + c == 180) {
			if(a == b || b == c || a == c) System.out.print("Isosceles");
			else System.out.print("Scalene");
		}
		else System.out.print("Error");
	}
}