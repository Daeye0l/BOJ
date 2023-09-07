package algorithmStudy;

import java.io.*;
import java.util.*;

public class GEO1085 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());

		int x_min = Math.min(x, w-x); // 직사각형의 세로선으로 이동하는 최소거리
		int y_min = Math.min(y, h-y); // 직사각형의 가로선으로 이동하는 최소거리
		
		System.out.print(Math.min(x_min, y_min)); // 세로와 가로 중 최소거리
	}
}