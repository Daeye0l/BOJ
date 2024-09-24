package prefixSum;

import java.io.*;
import java.util.*;

public class PS10211 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n];
			List<Integer> subArr = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j < n; j++) {
				int pSum = 0;
				for(int k = j; k < n; k++) {
					pSum += arr[k];
					subArr.add(pSum);
				}
			}
			Collections.sort(subArr);
			
			System.out.println(subArr.get(subArr.size()-1));
		}
	}
}