package algorithmStudy;

import java.io.*;

public class MD2581 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int sum = 0; // 소수의 합을 저장할 변수
		int min = 0; // 소수 중 최솟값을 저장할 변수
		
		for(int i = m; i <= n; i++) { // M이상 N이하를 반복
			boolean isDecimal = true; // 소수가 맞는지 저장할 변수
			
			// 2 ~ i-1에 있는 수로 나누어 떨어지면 소수가 아님 
			for(int j = 2; j < i; j++) { 
				if(i % j == 0) {
					isDecimal = false;
				}
			}
			
			// 소수가 맞다면 소수의 합에 더하고 최솟값을 저장
			if(isDecimal == true && i >= 2) {
				sum += i;
				if(min == 0) {
					min = i;
				}
			}
		}
		
		if(sum == 0) { // M이상 N이하에 소수가 하나도 없는 경우
			System.out.print(-1);
		}
		else {
			System.out.println(sum);
			System.out.print(min);
		}
	}
}