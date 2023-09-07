package algorithmStudy;

import java.math.BigInteger;
import java.util.Scanner;

public class M10757 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BigInteger a = input.nextBigInteger();
		BigInteger b = input.nextBigInteger();
		System.out.print(a.add(b));
		input.close();
	}
}