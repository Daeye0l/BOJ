package geometry;

import java.io.*;

public class GEO3053 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int r = Integer.parseInt(br.readLine());
		
		// 유클리드 기하학에서 원의 넓이는 PI x r^2
		/* 원의 정의는 중심에서 반지름 만큼의 거리만큼 떨어져 있는 점들의 집합이고
		   택시 기하학에서의 원의 점들의 위치를 거리식에 주어진 식에 기반하여 그리면
		   가로 세로 길이가 2 x r인 마름모가 그려지게 된다.*/
		System.out.printf("%.6f\n",Math.PI * Math.pow(r, 2));
		System.out.printf("%.6f",Math.pow(2 * r, 2) / 2);
	}
}