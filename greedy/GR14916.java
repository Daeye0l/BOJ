package greedy;

import java.io.*;

public class GR14916 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int change = Integer.parseInt(br.readLine()); // 거스름돈 입력받기
		int sum = 0; // 거스름돈 동전의 최소 개수를 저장
		
		while(change != 0) {
			if(change % 5 != 0) { // 거스름돈이 5원으로 나누어 떨어지지 않는 경우
				// 2원을 거슬러 주고 동전 개수를 증가시킴
				change -= 2;
				sum += 1;
			}
			else if(change % 5 == 0) { // 거스름돈이 5원으로 나누어 떨어지는 경우
				// 남은 모든 동전을 5원으로 거슬러줌
				sum += change / 5;
				change %= 5;
			}
			if(change < 0) { // 동전을 거슬러 줄 수 없는 경우
				System.out.println(-1);
				return;
			}
		}
	
		System.out.println(sum);
	}
}