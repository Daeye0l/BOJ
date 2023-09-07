package algorithmStudy;

import java.util.Scanner;

public class M1193 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int x = input.nextInt(); // X번째 분수 입력받기
		int f = 1; // 층 변수 초기화
		int b = 1; // 각 층의 마지막 분수가 몇 번째인지
		input.close();
		
		while(b < x) { // 찾으려고 하는 번째의 분수가 각 층의 마지막 분수보다 나중이라면
			f++; // 층 증가
			b = b + f; // 마지막 번째 수 증가
		}
		int i = b - x;
		if(f % 2 == 0) { // 짝수층이라면
			System.out.print((f-i) + "/" + (1+i));
		}
		else { // 홀수층이라면
			System.out.print((1+i) + "/" + (f-i));
		}
	}
}