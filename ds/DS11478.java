package ds;

import java.io.*;
import java.util.*;

public class DS11478 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		HashSet<String> set = new HashSet<>();
		
		// 문자열 S에 대한 모든 부분 문자열을 구하면서 집합에 이미 존재한다면 추가하지 않음
		for(int i = 0; i < s.length(); i++) {
			for(int j = i+1; j <= s.length(); j++) {
				String sub = s.substring(i, j);
				if(!set.contains(sub)) {
					set.add(sub);
				}
			}
		}
		System.out.println(set.size());
	}
}