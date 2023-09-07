package greedy;

import java.io.*;

public class GR5585 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int p = Integer.parseInt(br.readLine()); // 물건 가격
		int c = 1000 - p; // 거스름돈
		
		int[] change = {500, 100, 50, 10, 5, 1}; // 잔돈 배열 생성
		
		int sum = 0; // 잔돈의 개수를 저장할 배열 생성
		int i = 0; // 잔돈 배열의 인덱스
		// 거스름돈의 개수를 최소로 하기 위해 액면이 큰 순서대로 거슬러준다.
		while(i <= 5 && c > 0) {
			if(c / change[i] != 0) {
				sum += c / change[i];
				c %= change[i];
			}
			i++;
		}
		
		System.out.print(sum);
	}
}