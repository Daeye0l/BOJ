package algorithmStudy;

import java.util.ArrayList;
import java.util.Scanner;

public class M11005 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt(); // N 입력받기
		int b = input.nextInt(); // 진법 입력받기
		input.close();
		ArrayList<Character> list = new ArrayList<>();
		
		while(n > 0) {
			if(n % b < 10) { // 해당 자리의 수가 '0' ~ '9'일 때
				list.add((char)(n % b + 48));
			}
			else { // 해당 자리의 수가 'A' ~ 'Z'일 때
				list.add((char)(n % b + 55));
			}
			n /= b;
		}
		
		for(int i = list.size()-1; i >=0; i--) { // 거꾸로 출력하여 완성
			System.out.print(list.get(i));
		}
	}
}
