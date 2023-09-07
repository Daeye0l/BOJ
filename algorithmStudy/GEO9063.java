package algorithmStudy;

import java.io.*;
import java.util.*;

public class GEO9063 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 점의 개수 입력받기

		int[] x = new int[n]; // x좌표를 저장할 배열
		int[] y = new int[n]; // y좌표를 저장할 배열
		
		// 각 케이스의 x좌표와 y좌표를 분리해서 배열에 추가
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		
		if(n < 2) { // 점의 개수가 1개인 경우
			System.out.print(0);
		}
		else { // 점의 개수가 2개 이상인 경우
			int max_x = x[0]; int min_x = x[0];
			int max_y = y[0]; int min_y = y[0];

			for(int i = 1; i < n; i++) {
				if(max_x < x[i]) max_x = x[i];
				if(min_x > x[i]) min_x = x[i];
				if(max_y < y[i]) max_y = y[i];
				if(min_y > y[i]) min_y = y[i];
			}
			// 최대 x좌표와 최소 x좌표의 사이의 길이 * 최대 y좌표와 최소 y좌표의 사이의 길이
			System.out.print((max_x - min_x) * (max_y - min_y));
		}
	}
}
