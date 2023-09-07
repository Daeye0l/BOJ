package algorithmStudy;

import java.util.Scanner;

public class M2292 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt(); // N 입력받기
		input.close();
		// 1, 2 ~ 7, 8 ~ 19, 20 ~ 37, 38 ~ 61
		int count = 1; // 몇개의 방을 지나는지 세는 변수
		int i = 1; // 각 겹이 시작하는 번호
		while (n > i) {
			i += 6 * count;
			count += 1;
		}
		System.out.print(count);
	}
}