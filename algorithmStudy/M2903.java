package algorithmStudy;

import java.util.Scanner;

public class M2903 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		input.close();
		System.out.print((int)Math.pow((Math.pow(2, n) + 1), 2));
	}
}