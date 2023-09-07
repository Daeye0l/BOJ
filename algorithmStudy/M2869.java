package algorithmStudy;

import java.util.Scanner;

public class M2869 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int a = input.nextInt(); // 올라가는 길이
		int b = input.nextInt(); // 떨어지는 길이
		int v = input.nextInt(); // 정상지점
		int day = 0; // 소요되는 일수
		input.close();
		
		// 마지막 날은 올라가기만 하고 떨어지지 않음
		// 즉 a-b만큼씩 올라가다가 마지막에 a만큼 올라가므로 총 거리는 v-b
		if((v-b) % (a-b) == 0) {
			day = (v-b) / (a-b);
		}
		else {
			day = (v-b) / (a-b) + 1;
		}
		System.out.print(day);
	}
}