package algorithmStudy;

import java.io.*;

public class BRUTE1436 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // N 입력받기
		
		// 666이상의 자연수를 확인하여 정답 찾기
		int i = 666;
		int cnt = 0;
		while(true) {
			if(String.valueOf(i).contains("666")) { // 해당하는 자연수가 666을 포함하고 있다면
				cnt++; // 카운트 증가
			}
			if(cnt == n) { // 찾으려고 하는 N번째와 카운트가 같다면
				System.out.print(i); // 해당하는 자연수 출력
				break;
			}
			else i++;
		}
	}
}