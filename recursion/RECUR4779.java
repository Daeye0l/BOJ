package recursion;

import java.io.*;

public class RECUR4779 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = "";
		while((input = br.readLine()) != null && !input.equals(" ")) {
			int n = Integer.parseInt(input);
			boolean[] cantor = new boolean[(int)Math.pow(3, n)];
			
			recursion(cantor, 0, cantor.length-1);
			
			StringBuilder sb = new StringBuilder();
			for(int i = 0 ; i < cantor.length; i++) {
				// 제외되지 않은 부분은 -, 제외된 부분은 공백 출력
				if(!cantor[i]) sb.append("-");
				else sb.append(" ");
			}
			
			System.out.println(sb);
		}
	}
	
	private static void recursion(boolean[] cantor, int f, int r) {
		if(f < r) {
			int d = (r-f+1)/3;
			int p = f+d;
			int q = r-d;
			// 3등분 중 가운데 부분을 제외
			for(int i = p; i <= q; i++) {
				cantor[i] = true; 
			}
			recursion(cantor, f, p-1);
			recursion(cantor, q+1, r);
		}
	}
}