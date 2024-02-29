package ds;

import java.io.*;
import java.util.*;

public class DS14425 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 집합의 원소 개수
		int m = Integer.parseInt(st.nextToken()); // 테스트케이스 개수
		int result = 0;
		
		HashSet<String> set = new HashSet<>(); // 집합 생성
		for(int i = 0 ; i < n; i++) { // 문자열을 입력받아 집합에 추가
			set.add(br.readLine());
		}
		
		for(int i = 0; i < m; i++) { // 테스트케이스에 대해서 집합에 포함된다면 결과값 +1
			if(set.contains(br.readLine())) {
				result += 1;
			}
		}
		
		System.out.println(result);
	}
}