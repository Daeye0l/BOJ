package bruteforcing;

import java.io.*;
import java.util.*;

public class BRUTE7568 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 사람 수 입력받기
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 각 사람의 몸무게와 키 입력받기
			// 몸무게와 키를 분리해서 저장
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] rank = new int[N];
		for(int i = 0; i < N; i++) {
			int cnt = 0; // 현재 학생보다 덩치가 큰 사람의 수를 카운트할 변수
			int cur_w = arr[i][0]; // 현재 학생의 무게
			int cur_h = arr[i][1]; // 현재 학생의 키
			
			for(int j = 0; j < N; j++) {
				if(j != i) { // 자신이 아닌 학생에 대하여
					if(cur_w < arr[j][0] && cur_h < arr[j][1]) {
						cnt++;
					}
				}
			}
			
			rank[i] = cnt + 1; // 자신보다 덩치가 큰 사람의 수가 cnt이므로 등수는 cnt + 1
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i : rank) {
			sb.append(i).append(" ");
		}
		
		System.out.print(sb);
	}
}