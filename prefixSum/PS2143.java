package prefixSum;

import java.io.*;
import java.util.*;

public class PS2143 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		long cnt = 0;
		
		int n = Integer.parseInt(br.readLine());
		int[] arrA = new int[n];
		List<Long> pSumA = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		// A 배열 입력 받기
		for(int i = 0; i < n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		// A배열의 모든 부배열의 누적합 저장
		for(int i = 0; i < n; i++) {
			long pSum = 0;
			for(int j = i; j < n; j++) {
				pSum += arrA[j];
				pSumA.add(pSum);
			}
		}
		Collections.sort(pSumA);
		
		int m = Integer.parseInt(br.readLine());
		int[] arrB = new int[m];
		List<Long> pSumB = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		// B 배열 입력 받기
		for(int i = 0; i < m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		// B 배열의 모든 부배열의 누적합 저장
		for(int i = 0; i < m; i++) {
			long pSum = 0;
			for(int j = i; j < m; j++) {
				pSum += arrB[j];
				pSumB.add(pSum);
			}
		}
		Collections.sort(pSumB);
		
		int aIdx = 0; // A 부배열 누적합 탐색은 앞쪽부터
		int bIdx = pSumB.size()-1; // B 부배열 누적합 탐색은 뒤쪽부터
		while(aIdx < pSumA.size() && bIdx >= 0) {
			long sum = pSumA.get(aIdx) + pSumB.get(bIdx);
			// 두 합이 조건을 만족하는 경우
			if(sum == t) {
				// 부배열의 누적합이 같은 경우 두 배열에서 중복된 갯수를 구해서 곱하면 쌍의 갯수가 나옴
				long aValue = pSumA.get(aIdx);
				long bValue = pSumB.get(bIdx);
			
				long aCnt = 0; long bCnt = 0;
				while(aIdx < pSumA.size() && pSumA.get(aIdx) == aValue) {
					aCnt++;
					aIdx++;
				}
				while(bIdx >= 0 && pSumB.get(bIdx) == bValue) {
					bCnt++;
					bIdx--;
				}
				cnt += aCnt * bCnt;
			}
			// 두 합이 조건보다 작으면 더 큰 수를 적용
			else if(sum < t) aIdx++;
			// 두 합이 조건보다 크면 더 작은 수를 적용
			else if(sum > t) bIdx--;
		}
		
		System.out.println(cnt);
	}
}