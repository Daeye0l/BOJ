package prefixSum;

import java.io.*;
import java.util.*;

public class PS1806 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int minLength = Integer.MAX_VALUE;
		int start = 0; int end = 0; int sum = 0;
		while(end < n || sum >= s) {
			// 부분합이 s이상이라면 start 포인터를 오른쪽으로 이동해 원소를 하나씩 줄여가며 확인해서 최소값 갱신
			if(sum >= s) {
				minLength = Math.min(minLength, end - start);
				sum -= arr[start];
				start++;
			}
			// 부분합이 s보다 작다면 원소를 계속 더함
			else {
				sum += arr[end];
				end++;
			}
		}
		
		if(minLength == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(minLength);
	}
}