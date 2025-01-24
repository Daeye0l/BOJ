package math;

import java.io.*;

public class M31403 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String a = br.readLine();
		String b = br.readLine();
		String c = br.readLine();
		
		System.out.println(Integer.parseInt(a) + Integer.parseInt(b) - Integer.parseInt(c));
		System.out.println(Integer.parseInt(a+b) - Integer.parseInt(c));
	}
}