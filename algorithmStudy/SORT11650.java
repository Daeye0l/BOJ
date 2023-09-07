package algorithmStudy;

import java.io.*;
import java.util.*;

public class SORT11650 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 점의 개수 입력받기
		List<Coordinate> arr = new ArrayList<>(); // 각 점을 저장할 배열 생성
		
		// 각 점의 좌표를 입력 받아서 점을 배열에 넣기
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Coordinate c = new Coordinate(Integer.parseInt(st.nextToken()),
										  Integer.parseInt(st.nextToken()));
			arr.add(c);
		}
		
		Collections.sort(arr, new Comparator<Coordinate>() {
			public int compare(Coordinate c1, Coordinate c2) {
				if(c1.x == c2.x) {
					return c1.y - c2.y;
				}
				else {
					return c1.x - c2.x;
				}
			}
		});
		
		for(Coordinate c : arr) {
			System.out.println(c.x + " " + c.y);
		}
	}
}

// 좌표 클래스 생성
class Coordinate {
	int x;
	int y;
	
	public Coordinate(int a, int b) {
		this.x = a;
		this.y = b;
	}
}