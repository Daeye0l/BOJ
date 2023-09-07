package algorithmStudy;

import java.io.*;
import java.util.*;

public class GEO14215 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 세 변 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		// 세 변 중 최댓값 max 구하기
		int max = a;
		max = Math.max(max, b);
		max = Math.max(max, c);

		if(max == a) { // 가장 긴 변이 a인 경우
			// 정삼각형이거나 가장 긴 변이 이미 나머지 두 변의 합보다 작은 경우
			if((a == b && b == c) || (a < b + c)) System.out.print(a + b + c);
			else { 
				a = b + c - 1;
				System.out.print(a + b + c);
			}
		}
		else if(max == b) { // 가장 긴 변이 b인 경우
			// 정삼각형이거나 가장 긴 변이 이미 나머지 두 변의 합보다 작은 경우
			if((a == b && b == c) || (b < a + c)) System.out.print(a + b + c);
			else {
				b = a + c - 1;
				System.out.print(a + b + c);
			}
		}
		else { // 가장 긴 변이 c인 경우
			// 정삼각형이거나 가장 긴 변이 이미 나머지 두 변의 합보다 작은 경우
			if((a == b && b == c) || (c < a + b)) System.out.print(a + b + c);
			else {
				c = a + b - 1;
				System.out.print(a + b + c);
			}
		}
	}
}