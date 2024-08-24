package ds;

import java.io.*;
import java.util.*;

public class DS7785 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashSet<String> set = new HashSet<>();
		
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			if(!set.contains(name)) {
				set.add(name);
			}
			else {
				set.remove(name);
			}
		}
		
		List<String> nameList = new ArrayList<>(set); // 집합의 원소로 리스트 생성
		Collections.sort(nameList); // 사전순으로 정렬
		for(int i = nameList.size()-1; i >= 0; i--) { // 역순으로 출력
			System.out.println(nameList.get(i));
		}
	}
}