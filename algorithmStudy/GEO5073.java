package algorithmStudy;

import java.io.*;
import java.util.StringTokenizer;

public class GEO5073 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			// 세 변의 길이 입력받기
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 세 변 중에서 길이가 가장 긴 변을 max
			int max = a;
			max = Math.max(max, b);
			max = Math.max(max, c);
			
			if(a == 0 && b == 0 && c == 0) { // 0 0 0이라면 종료
				return;
			}
			else {
				// 세 변 중 가장 긴 변이 a인 경우
				if(max == a) {
					// 정삼각형인 경우
					if(a == b && b == c) System.out.println("Equilateral");
					// 정삼각형이 아닌 경우
					else {
						if(b + c <= a) System.out.println("Invalid");
						else {
							if(a == b || b == c || a == c) System.out.println("Isosceles");
							else System.out.println("Scalene");
						}
					}
				}
				// 세 변 중 가장 긴 변이 b인 경우
				else if(max == b) {
					// 정삼각형인 경우
					if(a == b && b == c) System.out.println("Equilateral");
					// 정삼각형이 아닌 경우
					else {
						if(a + c <= b) System.out.println("Invalid");
						else {
							if(a == b || b == c || a == c) System.out.println("Isosceles");
							else System.out.println("Scalene");
						}
					}
				}
				// 세 변 중 가장 긴 변이 c인 경우
				else {
					// 정삼각형인 경우
					if(a == b && b == c) System.out.println("Equilateral");
					// 정삼각형이 아닌 경우
					else {
						if(a + b <= c) System.out.println("Invalid");
						else {
							if(a == b || b == c || a == c) System.out.println("Isosceles");
							else System.out.println("Scalene");
						}
					}
				}
			}
		}
	}
}