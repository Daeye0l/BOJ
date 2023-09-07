package algorithmStudy;
import java.util.Scanner;
public class M2745 {
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		String n = input.next(); // 수 N 입력받기
		int b = input.nextInt(); // 진법 B 입력받기
		input.close();
		
		int result = 0; // 결과를 저장할 변수
		int temp = 1; // 각 자리에 곱할 진법을 누적시킬 변수
		for(int i = n.length()-1; i >= 0; i--) { // N의 일의 자리부터 반복
			char c = n.charAt(i); // N의 i번째 인덱스의 문자를 c
			
			if(c >= 65 && c <= 90) { // c가 문자 'A' ~ 'Z'의 값일때
				result += (c - 55) * temp; // c-55 = c-65+10
			}
			else {
				result += (c - 48) * temp;
			}
			temp *= b; // 진법을 거듭제곱
		}
		System.out.println(result);
	}
}