package prefixSum;

import java.io.*;
import java.util.*;

public class PS2003 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		long pSum = 0;
		long cnt = 0;
		int start = 0;
		int end = 0;
		while(true) {
			if(pSum >= m) {
				pSum -= arr[start++];
			}
			// end 포인터가 끝까지 도달하고도 누적합이 m보다 작다면 더이상 가능성이 없으므로 종료
			else if(end == n) {
				break;
			}
			else {
				pSum += arr[end++];
			}
			if(pSum == m) cnt++;
		}
		
		System.out.println(cnt);
	}
}