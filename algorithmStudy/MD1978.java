package algorithmStudy;

import java.io.*;
import java.util.*;

public class MD1978 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 개수 입력받기
		int cnt = 0; // 소수의 개수를 저장할 변수
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			boolean isDecimal = true; // 주어진 수가 소수인지 여부를 저장하는 변수
			int k = Integer.parseInt(st.nextToken()); // 토큰 단위로 k지정
			
			for(int j = 2; j < k; j++) { //k가 2 ~ k-1까지의 수로 나누어진 경우
				if(k % j == 0) {
					isDecimal = false; // 소수가 아님
				}
			}
			
			if(isDecimal == true && k >= 2) { // 1은 소수가 아니기 때문에 k범위 조정
				cnt += 1;
			}
		}
		System.out.print(cnt);
	}
}