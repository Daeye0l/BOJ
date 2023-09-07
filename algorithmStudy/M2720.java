package algorithmStudy;

import java.util.Scanner;

public class M2720 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int t = input.nextInt(); // 케이스 개수 입력받기
		int[] c = new int[t]; // 거스름돈을 저장할 배열
		
		for(int i = 0; i < t; i++) {
			c[i] = input.nextInt(); // 케이스 개수 만큼의 거스름돈을 입력받아 저장
		}
		input.close();
		
		for(int x : c) {
			System.out.print((x / 25) + " ");
			x = x % 25;
			System.out.print((x / 10) + " ");
			x = x % 10;
			System.out.print((x / 5) + " ");
			x = x % 5;
			System.out.print((x / 1) + " ");
			System.out.println();
		}
		
		
	}
}