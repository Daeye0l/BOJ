package recursion;

import java.io.*;
import java.util.*;

public class RECUR24060 {
	static int[] A;
	static int[] tmp;
	static int k, cnt = 0, knum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		A = new int[n];
		tmp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(A, 0, A.length-1);
		if(cnt < k) System.out.println(-1);
		else System.out.println(knum);
	}
	
	private static void mergeSort(int[] A, int p, int r) {
		if(p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
 	}
	
	private static void merge(int[] A, int p, int q, int r) {	
		int i = p; int j = q + 1; int t = 0;
		while(i <= q && j <= r) {
			if(A[i] <= A[j]) {
				tmp[t++] = A[i++];
			}
			else {
				tmp[t++] = A[j++];
			}
		}
		// 분할한 왼쪽 부분의 원소가 남은 경우
		while(i <= q) {
			tmp[t++] = A[i++];
		}
		// 분할한 오른쪽 부분의 원소가 남은 경우
		while(j <= r) {
			tmp[t++] = A[j++];
		}
		// 병합 결과를 다시 원본 배열에 저장
		i = p; t = 0;
		while(i <= r) {
			int temp = tmp[t++];
			A[i++] = temp;
			cnt++; // 저장 횟수 증가
			if(cnt == k) knum = temp; // k번째 저장되는 수 저장 
		}
	}
}