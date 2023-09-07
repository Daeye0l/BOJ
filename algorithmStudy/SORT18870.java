package algorithmStudy;

import java.io.*;
import java.util.*;

public class SORT18870 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 좌표의 개수 입력받기
		List<Integer> arr = new ArrayList<>(); // 좌표를 저장할 리스트
		List<Integer> tmp = new ArrayList<>();
		
		// 좌표를 입력받아 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int j = Integer.parseInt(st.nextToken());
			arr.add(j); tmp.add(j);
		}
		
		// 배열 정렬
		Collections.sort(arr);
		
		// 해시맵 생성
		HashMap<Integer, Integer> map = new HashMap<>();
		
		int rank = 0;
		for(int x : arr) { // 배열의 원소를 반복하면서
			if(!map.containsKey(x)) { // 맵에 해당 키가 없으면
				map.put(x, rank); // 키와 값(rank)를 추가해줌
				rank++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int x : tmp) {
			sb.append(map.get(x)).append(" ");
		}
		
		System.out.print(sb);
	}
}