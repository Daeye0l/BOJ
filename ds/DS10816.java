package ds;

import java.io.*;
import java.util.*;

public class DS10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine()); // n 입력받기

		HashMap<Integer, Integer> map = new HashMap<>(); // 해시맵 생성

		// 상근이가 가지고 있는 숫자카드의 번호와 개수로 해시맵의 키와 밸류를 구성 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int k = Integer.parseInt(st.nextToken());
			if(!map.containsKey(k)) { // 해시맵에 해당 수가 없다면
				map.put(k, 1); // 추가하고 밸류인 개수를 1로 지정
			}
			else { // 이미 해시맵에 해당 수가 있다면
				map.put(k, map.get(k) + 1); // 값을 1 증가
			}
		}

		int m = Integer.parseInt(br.readLine()); // m 입력받기

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			int k = Integer.parseInt(st.nextToken());
			// 찾으려고 하는 값이 맵에 있다면 결과에 개수를 추가하고 없다면 0추가
			if(map.containsKey(k)) bw.write(map.get(k) + " "); 
			else bw.write("0 ");
		}
		bw.flush();
	}
}