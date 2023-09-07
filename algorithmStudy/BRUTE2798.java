package algorithmStudy;

import java.io.*;
import java.util.*;

public class BRUTE2798 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 카드의 개수 n과 고른 카드의 합 M
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 카드에 쓰여 있는 수를 입력 받아서 배열에 저장
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// M을 넘지 않으면서 M에 최대한 가까운 합을 max에 저장
		int max = 0;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				for(int k = j+1; k < n; k++) {
					if(arr[i] + arr[j] + arr[k] <= m) max = Math.max(max, arr[i] + arr[j] + arr[k]);
					else continue;
				}
			}
		}
		
		System.out.print(max);
	}
}