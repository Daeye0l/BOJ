package algorithmStudy;

import java.io.*;
import java.util.*;

public class SORT11651 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 점의 개수 입력받기
		List<Coordinate2> arr = new ArrayList<>(); // 각 점을 저장할 배열 생성

		// 각 점의 좌표를 입력 받아서 점을 배열에 넣기
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Coordinate2 c = new Coordinate2(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
			arr.add(c);
		}

		Collections.sort(arr, new Comparator<Coordinate2>() {
			public int compare(Coordinate2 c1, Coordinate2 c2) {
				if(c1.y == c2.y) {
					return c1.x - c2.x;
				}
				else {
					return c1.y - c2.y;
				}
			}
		});

		for(Coordinate2 c : arr) {
			System.out.println(c.x + " " + c.y);
		}
	}
}

// 좌표 클래스 생성
class Coordinate2 {
	int x;
	int y;

	public Coordinate2(int a, int b) {
		this.x = a;
		this.y = b;
	}
}