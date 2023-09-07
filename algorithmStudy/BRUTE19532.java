package algorithmStudy;

import java.io.*;
import java.util.*;

public class BRUTE19532 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		// -999 ~ 999의 범위의 모든 정수로 x, y를 반복하여 방정식의 해 찾기
		for(int x = -999; x <= 999; x++) {
			for(int y = -999; y <= 999; y++) {
				if((a*x + b*y == c) && (d*x + e*y == f)) {
					System.out.print(x + " " + y);
				}
			}
		}
		
	}
}