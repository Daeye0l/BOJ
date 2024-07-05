package tree;

import java.io.*;
import java.util.*;

public class TREE5052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		boolean isConsitent = true;
		
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());	
			String[] phoneNumber = new String[n];
			
			for(int j = 0 ; j < n; j++) {
				phoneNumber[j] = br.readLine();
			}
			
			// 전화번호 목록을 정렬
			Arrays.sort(phoneNumber);
			
			// 먼저있는 전화번호가 다음 전화번호의 시작으로 포함된다면 일관성이 없음(정렬했기 때문에 가능함)
			for(int j = 0; j < n-1; j++) {
				if(phoneNumber[j+1].startsWith(phoneNumber[j])) isConsitent = false;
			}
			
			if(!isConsitent) System.out.println("NO");
			else System.out.println("YES");
			
			isConsitent = true;
		}
	}
}