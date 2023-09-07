package algorithmStudy;

import java.io.*;
import java.math.*;

public class GEO15894 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger b = new BigInteger(br.readLine());
		System.out.print(b.multiply(new BigInteger("4")));
	}
}