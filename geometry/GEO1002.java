package geometry;

import java.io.*;
import java.util.*;

public class GEO1002 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine()); // 케이스 개수 입력받기

		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 여섯 정수 입력받기
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			
			// 두 점 사이의 거리를 제곱한 변수를 생성
			double distance_pow = Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
			
			if(x1 == x2 && y1 == y2 && r1 == r2) { // 두 원이 일치하는 경우
				System.out.println(-1); // 류재명이 있을 수 있는 위치의 개수는 무한대이다.
			}
			// 두 원의 접점이 없는 경우
			else if(distance_pow > Math.pow(r1 + r2, 2)) { // 두 원이 서로 떨어져서 만나지 않는 경우
				System.out.println(0); // 류재명이 있을 수 있는 위치의 개수는 0
			}
			else if(distance_pow < Math.pow(r2 - r1, 2)) { // 하나의 원 안에 다른 하나의 원이 있지만 접점이 없는 경우
				System.out.println(0); // 류재명이 있을 수 있는 위치의 개수는 0
			}
			else if(distance_pow == Math.pow(r2 - r1, 2)) { // 두 원이 내접하는 경우
				System.out.println(1); // 류재명이 있을 수 있는 위치의 개수는 1
			}
			else if(distance_pow == Math.pow(r1 + r2, 2)) { // 두 원이 외접하는 경우
				System.out.println(1); // 류재명이 있을 수 있는 위치의 개수는 1
			}
			else { // 나머지 경우에서 류재명이 있을 수 있는 위치는 2개이다.
				System.out.println(2);
			}
		}
	}
}