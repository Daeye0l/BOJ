package greedy;

import java.io.*;
import java.util.*;

public class GR13305 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 도시의 개수 입력받기

		long[] distance = new long[n-1]; // 인접한 도시 사이의 거리를 저장할 배열
		long[] price = new long[n]; // 각 도시의 리터당 기름 가격을 저장할 배열 생성

		// 도시 사이의 거리를 입력받아 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n-1; i++) {
			distance[i] = Long.parseLong(st.nextToken());
		}

		// 도시의 기름 가격을 입력받아 저장
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			price[i] = Long.parseLong(st.nextToken());
		}

		// 왼쪽 도시에서 오른쪽 도시로 이동하는 최소 비용은
		// 이전 도시보다 더 싼 주유소가 나올 때까지 쉬지 않고 이동하는 것이다.

		long min_price = price[0]; // 가장 싼 리터당 가격을 저장
		long sum = 0; // 오른쪽 도시로 가는 최소 비용

		for(int i = 0; i < n-1; i++) {
			if(price[i] < min_price) min_price = price[i];
			sum += min_price * distance[i];
		}

		System.out.print(sum);
	}
}