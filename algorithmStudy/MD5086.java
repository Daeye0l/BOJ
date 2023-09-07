package algorithmStudy;

import java.util.Scanner;

public class MD5086 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int a;
		int b;
		
		while(true) {
			a = input.nextInt();
			b = input.nextInt();
			
			if(a == 0 && b == 0) {
				return;
			}
			else if(b % a == 0) {
				System.out.println("factor");
			}
			else if(a % b == 0) {
				System.out.println("multiple");
			}
			else {
				System.out.println("neither");
			}
		}
	}
}