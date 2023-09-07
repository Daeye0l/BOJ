package algorithmStudy;

import java.io.*;

public class S27866 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int n = Integer.parseInt(br.readLine());
		
		System.out.print(s.charAt(n-1));
	}
}