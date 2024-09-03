package ds;

import java.io.*;
import java.util.*;

public class DS25192 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int cnt = 0;
		Set<String> set = new HashSet<>();
		
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			// 새로운 사람이 입장하면 모든 사람이 다시 인사할 수 있으므로 집합에서 닉네임을 모두 비움
			if(s.equals("ENTER")) {
				set.removeAll(set);
			}
			else { // 인사한 사람의 닉네임이 집합에 추가 됐다면 횟수 증가
				boolean result = set.add(s);
				if(result) cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}