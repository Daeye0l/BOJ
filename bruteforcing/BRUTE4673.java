package bruteforcing;

public class BRUTE4673 {

	public static void main(String[] args) {
		int[] number = new int[10001]; // 1 ~ 10000의 수를 저장할 배열 생성
		
		for(int i = 1; i <= 10000; i++) { // 배열에 1 ~ 10000저장
			number[i] = i;
		}
		
		for(int i = 1; i <= 10000; i++) {
			int cur = i; // 현재 수 지정
			int not_self = 0; // 셀프 넘버가 아닌 수를 저장할 변수
			
			not_self += cur; // 현재 수를 not_self에 먼저 더하고
			// 현재 수의 각 자리수를 누적해서 not_self에 더함
			while(cur > 0) {
				not_self += cur % 10;
				cur /= 10;
			}
			
			// number배열의 not_self인덱스의 값을 0으로 바꿔줌
			if(not_self <= 10000 && number[not_self] != 0) {
				number[not_self] = 0;
			}
		}
		
		// 셀프넘버인 수들만 출력
		for(int i : number) {
			if(i != 0) {
				System.out.println(i);
			}
		}
	}
}