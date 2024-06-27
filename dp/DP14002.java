package dp;

import java.io.*;
import java.util.*;

public class DP14002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 수열의 길이
		int[] numbers = new int[n]; // 수열을 저장할 배열
		int[] dp = new int[n]; // LIS를 저장할 배열
		int[] prevIndex = new int[n]; // LIS가 갱신될 때마다 이전 인덱스를 저장(즉 LIS구성 원소 중 뒤에서 2번째 인덱스)
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < n; i++) {
			dp[i] = 1;
			prevIndex[i] = -1;
		}
		
		int maxLength = 1; // LIS의 길이
		int maxIndex = 0; // LIS를 구성하는 원소 중 최대값의 인덱스
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < i; j++) {
				if(numbers[i] > numbers[j] && dp[j] + 1 > dp[i]) {
					// LIS의 최대 길이가 갱신될 때마다 이전 인덱스를 저장  
					dp[i] = dp[j] + 1;
					prevIndex[i] = j;
				}
				// LIS의 최대 길이와 인덱스 갱신
				if(dp[i]> maxLength) {
					maxLength = dp[i];
					maxIndex = i;
				}
			}
		}
		
		ArrayList<Integer> lisSequence = new ArrayList<>();
		while(maxIndex != -1) {
			lisSequence.add(numbers[maxIndex]); // 최대값의 인덱스를 사용해 수를 저장
			maxIndex = prevIndex[maxIndex]; // 그 인덱스가 어디서 왔는지로 다시 초기화
		}
		
		System.out.println(maxLength);
		for(int i = lisSequence.size()-1; i >= 0; i--) {
			System.out.print(lisSequence.get(i) + " ");
		}
	}
}