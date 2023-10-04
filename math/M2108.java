package math;

import java.io.*;
import java.util.*;

public class M2108 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 수의 개수 입력받기
		
		int[] arr = new int[N]; // 입력 받은 수를 저장할 배열
		HashMap<Integer, Integer> map = new HashMap<>(); // 입력받은 수와 빈도를 연결하는 맵
		
		int sum = 0; // 수들의 합
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine()); // 수 입력받기
			
			arr[i] = num; // 배열에 저장
			sum += num;
			if(!map.containsKey(num)) map.put(num, 1); // 맵에 없는 값이라면 추가하고 빈도 1로 설정
			else map.put(num, map.get(num) + 1); // 맵에 있는 값이라면 빈도 1 증가
		}
		
		Arrays.sort(arr); // 배열 정렬
		
		int max_value = Collections.max(map.values()); // 최대 빈도
		ArrayList<Integer> mode = new ArrayList<>(); // 최빈값을 넣을 배열
		for(int key : map.keySet()) { // 빈도가 최대 빈도와 같은 수들을 리스트에 저장 
			if(map.get(key) == max_value) mode.add(key);
		}
		Collections.sort(mode); // 정렬
		
		System.out.println(Math.round((double)sum / N));
		System.out.println(arr[N / 2]);
		if(mode.size() == 1) {
			System.out.println(mode.get(0));
		}
		else { // 리스트에 원소가 2개 이상인 경우 두 번재로 작은 값 출력
			System.out.println(mode.get(1));
		}
		System.out.println(arr[N-1] - arr[0]);
	}
}