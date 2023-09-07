package algorithmStudy;

import java.io.*;
import java.util.*;

public class SORT10814 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 회원수 입력받기
		
		String[][] arr = new String[n][2]; // 회원의 나이와 이름을 저장할 배열 생성
		
		// 나이와 이름을 입력받아 배열에 저장하기
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = st.nextToken();
			arr[i][1] = st.nextToken();
		}
		
		// 나이순, 안정정렬로 정렬
		Arrays.sort(arr, new Comparator<String[]>() {
			public int compare(String[] s1, String[] s2) {
				return Integer.parseInt(s1[0]) - Integer.parseInt(s2[0]);
			}
		});
		
		for(int i = 0; i < n; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
	}
}