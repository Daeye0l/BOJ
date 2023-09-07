package greedy;

import java.io.*;
import java.util.*;

public class GR2217 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 로프의 개수 입력받기
		int[] w = new int[n]; // 각 로프가 버틸 수 있는 최대 중량을 저장할 배열
		
		// 각 로프가 버틸 수 있는 최대 중량을 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			w[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(w); // 배열을 오름차순으로 정렬
		
		int max = 0; // 로프들을 이용하여 들 수 있는 최대 중량
		for(int i = 0; i < n; i++) {
			if(i == 0) max = w[i] * n; // n개의 모든 로프를 사용하려면 가장 약한 로프가 버틸 수 있는 중량이 모든 로프에 걸려야 함.
			else { // 로프의 개수를 하나씩 줄여가면서 들수 있는 최대 중량을 다시 구한다.
				if(max < w[i] * (n - i)) max = w[i] * (n - i);
			}
		}
		
		System.out.print(max);
	}
}