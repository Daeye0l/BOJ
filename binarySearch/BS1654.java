package binarySearch;

import java.io.*;
import java.util.*;

public class BS1654 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 개수
		int n = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수
		
		// 이미 가지고 있는 랜선의 길이를 입력받아 저장
		int[] lan = new int[k];
		int max = 0;
		for(int i = 0; i < k; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, lan[i]);
		}
		
		/* 랜선의 개수가 n이 되도록 하는 최대 랜선의 길이를 구하기 위해 
		   랜선의 길이에 대하여 이분탐색 */
		long f = 1;
		long l = max;
		while(f <= l) {
			long m = (f + l) / 2;
			long cnt = 0;
			
			// 이미 가지고 있는 랜선들을 m의 길이로 잘랐을 때의 랜선의 개수
			for(int x : lan) {
				cnt += x / m; 
			}
			
			// 개수가 필요한 양 이상이라면 현재 m보다 큰 영역에서 탐색
			if(cnt >= n) {
				f = m + 1;
			}
			else { // 개수가 필요한 양보다 적다면 현재 m보다 작은 영역에서 탐색
				l = m - 1;
			}
		}
		System.out.print(f-1);
	}
}