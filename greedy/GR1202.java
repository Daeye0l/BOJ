package greedy;

import java.io.*;
import java.util.*;

public class GR1202 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 보석의 개수
		int K = Integer.parseInt(st.nextToken()); // 가방의 개수
		
		int[][] jewels = new int[N][2]; // 보석의 무게와 가격을 저장
		Integer[] bags = new Integer[K]; // 가방에 담을 수 있는 최대 무게 저장
		
		// 보석의 무게와 가격을 입력 받아 저장하고 가방에 담을 수 있는 최대 무게를 입력 받아 저장한다.
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewels[i][0] = Integer.parseInt(st.nextToken());
			jewels[i][1] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}
		
		// 보석과 가방을 각각 무게 오름차순으로 정렬 한다.
		Arrays.sort(jewels, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
					return a[0] - b[0];
			}
		});
		Arrays.sort(bags);
		
		// 보석을 담을 우선순위 큐, 가격 내림차순으로 정렬한다.
		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return b[1] - a[1];
			}
		});
		
		long max = 0; // 보석 가격의 합의 최댓값
		for(int i = 0, j = 0; i < K; i++) {
			while(j < N && jewels[j][0] <= bags[i]) { // 보석의 무게가 가방에 들어갈 수 있다면
				que.add(jewels[j]); // 우선순위 큐에 넣어서 가격 내림차순으로 정렬
				j++;
			}
			if(!que.isEmpty()) {
				max += que.remove()[1]; // 가격이 가장 큰 보석을 훔친다.
			}
		}
		
		System.out.println(max);
	}
}