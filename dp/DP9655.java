package dp;

import java.io.*;

public class DP9655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 돌의 개수 입력받기
		
		/* 상근이와 창영이가 어떻게 가져가도 한 턴이 끝나면 돌이 짝수개씩 사라지므로
		돌이 홀수개라면 상근이가 이기고 아닌경우 창영이가 이긴다. */
		if(n % 2 == 0) System.out.println("CY");
		else System.out.println("SK");
	}
}