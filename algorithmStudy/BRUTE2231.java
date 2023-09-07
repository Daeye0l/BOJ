package algorithmStudy;

import java.io.*;

public class BRUTE2231 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 자연수 N 입력받기
		
		boolean check = false; // 생성자가 있는지 체크하는 변수
		
		// n보다 작은 모든 자연수를 대상으로 생성자가 있는지 확인하기
		for(int i = 1; i < n; i++) {
			int sum = 0; // 분해합을 저장할 변수
			
			// 분해합을 구해서 sum에 저장
			int k = i;
			while(k > 0) {
				sum += k % 10;
				k = k / 10;
			}
			
			// 어떤 자연수와 그 자연수의 분해합을 더한 값이 n인경우
			if(i + sum == n) {
				check = true;
				System.out.print(i); // 생성자 출력
				break;
			}
			else continue;
		}
		
		// 생성자가 없는 경우
		if(check == false) System.out.print(0);
	}
}