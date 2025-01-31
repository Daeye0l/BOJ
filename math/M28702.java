package math;

import java.io.*;

public class M28702 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int next = 0;
		for(int i = 0; i < 3; i++) {
			String s = br.readLine();
			if(!s.equals("Fizz") && !s.equals("Buzz") && !s.equals("FizzBuzz")) {
				next = Integer.parseInt(s) + 3-i;
				break;
			}
		}
		
		if(next % 3 == 0 && next % 5 == 0) {
			System.out.println("FizzBuzz");
		}
		else if(next % 3 == 0 && next % 5 != 0) {
			System.out.println("Fizz");
		}
		else if(next % 3 != 0 && next % 5 == 0) {
			System.out.println("Buzz");
		}
		else System.out.println(next);
	}
}