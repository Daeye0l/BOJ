package greedy;

import java.io.*;
import java.util.*;

public class GR10610 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String n = br.readLine(); // N 입력밥기
		Integer[] arr = new Integer[n.length()]; // N의 각 자리수를 저장할 배열
		
		if(!n.contains("0")) { // 30의 배수가 되기 위해서는 0이 최소 1개는 있어야 함.
			System.out.print(-1);
		}
		else {
			int sum = 0; // 3의 배수가 맞는지 확인하기 위해 각 자리수를 더하여 저장
			
			for(int i = 0; i < n.length(); i++) {
				// N의 각 자리수를 배열에 저장
				arr[i] = Character.getNumericValue(n.charAt(i));
				sum += arr[i];
			}
			
			Arrays.sort(arr, Collections.reverseOrder()); // 내림차순 정렬
			
			if(sum % 3 == 0) { // 3의 배수가 맞다면
				StringBuilder sb = new StringBuilder();
				for(int j = 0; j < n.length(); j++) {
					sb.append(arr[j]);
				}
				System.out.print(sb);
			}
			else { // 3의 배수가 아니라면
				System.out.print(-1);
			}
		}
	}
}