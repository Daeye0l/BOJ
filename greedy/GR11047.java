package greedy;

import java.io.*;
import java.util.*;

public class GR11047 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n과 k 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] money = new int[n];
		
		// 각 동전의 가치를 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}
		
		int sum = 0; // 필요한 동전의 개수를 저장할 변수
		int i = money.length - 1; // 동전 가치 배열의 마지막 인덱스
		// 동전을 최소로 사용하여 가치가 k가 되게 하는 방법은 가치가 큰 동전을 우선으로 선택하는 경우이다.
		while(i >= 0 && k >= 0) {
			if(k / money[i] != 0) {
				sum += k / money[i];
				k %= money[i];
			}
			i--;
		}
		
		System.out.print(sum);
	}
}