package string;

import java.io.*;

public class S11719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력값을 얼마나 받을 지 문제에 나와있지 않으므로 EOF로 처리
		String s = "";
		while((s = br.readLine()) != null) {
			bw.write(s + '\n');
			
		}
		bw.flush();
	}
}