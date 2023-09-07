package algorithmStudy;

import java.io.*;
import java.util.*;

public class S1152 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = 0;
		
		while(st.hasMoreTokens()) {
			st.nextToken();
			cnt += 1;
		}
		
		System.out.print(cnt);
	}
}