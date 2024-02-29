package ds;

import java.io.*;
import java.util.*;

public class DS1620 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 도감에 수록되어 있는 포켓몬 개수
		int m = Integer.parseInt(st.nextToken()); // 문제의 개수
		
		// 1번부터 n번까지의 포켓몬을 map과 배열에 저장
		HashMap<String, Integer> map = new HashMap<>();
		String[] names = new String[n+1];
		for(int i = 1; i <= n; i++) {
			String name = br.readLine();
			map.put(name, i);
			names[i] = name;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			String input = br.readLine();
			if(map.containsKey(input)) { // 입력이 숫자인 경우
				sb.append(map.get(input) + "\n");
			}
			else { // 입력이 문자열인 경우
				sb.append(names[Integer.parseInt(input)] + "\n");
			}
		}
		System.out.println(sb);
	}
}