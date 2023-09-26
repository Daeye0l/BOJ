package greedy;

import java.io.*;
import java.util.*;

public class GR16953 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// a와 b 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		long a = Integer.parseInt(st.nextToken());
		long b = Integer.parseInt(st.nextToken());

		int cnt = 0;
		while(b != a) { // b와 a가 같아질 때까지 역으로 접근
			if(b < a) { // b가 a보다 작은 경우
				System.out.print(-1);
				return;
			}
			else { // b가 a보다 큰 경우
				if(b % 2 == 0) { // b가 2로 나누어 떨어지면
					b /= 2; // b를 2로 나누고
					cnt++; // 카운트 증가
				}
				else if(b % 10 == 1) { // b의 1의 자리가 1이라면
					b /= 10; // 1의 자리를 버리고
					cnt++; // 카운트 증가
				}
				else {
					System.out.print(-1);
					return;
				}
			}
		}
		System.out.print(cnt + 1);
	}
}