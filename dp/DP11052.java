package dp;

import java.io.*;
import java.util.*;

public class DP11052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 카드의 개수 입력받기
		
		// 카드의 개수가 i개인 카드팩의 가격과 카드팩의 카드 1개당 가치를 저장
		float[][] p_val = new float[2][n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			p_val[0][i] = Float.parseFloat(st.nextToken()); // 카드팩의 가격과
			p_val[1][i] = p_val[0][i] / i; // 카드 1개당 가치를 저장
		}
		
		Arrays.sort();
		
		for(int i = 0; i <= 1; i++) {
			for(int j = 0 ; j <= n; j++) {
				System.out.print(p_val[i][j] + " ");
			}
			System.out.println();
		}
	}
}