package algorithmStudy;

import java.io.*;

public class S11718 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String s = "";
		while(true) {
			s = br.readLine();
			if(s == null) {
				break;
			}
			sb.append(s);
			sb.append("\n");
		}
		System.out.print(sb);
	}
}