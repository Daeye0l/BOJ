package ds;

import java.io.*;
import java.util.*;

public class DS26069 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<>();
		// 처음에는 총총이만 춤을 추고 있음
		set.add("ChongChong");
		int cnt = 1;
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			// 두 사람이 만났을 때 춤을 추고 있는 사람이 있다면 다른 한 사람도 춤을 춤
			if(set.contains(a)) {
				if(!set.contains(b)) {
					set.add(b);
					cnt++;
				}
			}
			else if(set.contains(b)) {
				if(!set.contains(a)) {
					set.add(a);
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}