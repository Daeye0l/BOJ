package prefixSum;

import java.io.*;
import java.util.*;

public class PS21758 {

	public static void main(String[] args) throws IOException {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 
		 int n = Integer.parseInt(br.readLine());
		 int[] arr = new int[n+1];
		 long[] pSum = new long[n+1];
		 
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 for(int i = 1; i <= n; i++) {
			 arr[i] = Integer.parseInt(st.nextToken());
		 }
		 for(int i = 1; i <= n; i++) {
			pSum[i] = pSum[i-1] + arr[i];
		 }
		 
		 long max = 0;
		 // 벌통이 오른쪽 끝에 있는 경우(꿀벌 하나는 왼쪽 끝에 고정)
		 for(int i = 2; i < n; i++) {
			 max = Math.max(max, (pSum[n]-arr[1]-arr[i]) + (pSum[n]-pSum[i]));
		 }
		 // 벌통이 왼쪽 끝에 있는 경우(꿀벌 하나는 오른쪽 끝에 고정)
		 for(int i = 2; i < n; i++) {
			 max = Math.max(max, (pSum[n-1]-arr[i]) + pSum[i-1]);
		 }
		 // 벌통이 중간에 있는 경우(꿀벌들을 양쪽 끝에 고정)
		 for(int i = 2; i < n; i++) {
			 max = Math.max(max, (pSum[i]-arr[1]) + (pSum[n-1]-pSum[i-1]));
		 }
		 
		 System.out.println(max);
	}
}