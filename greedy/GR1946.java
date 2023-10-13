package greedy;

import java.io.*;
import java.util.*;

public class GR1946 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수 입력받기
		
		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine()); // 지원자 수 입력받기
			
			int[][] arr = new int[N][2]; // 지원자의 성적을 각각 저장할 배열 생성
			for(int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[j][0] = Integer.parseInt(st.nextToken());
				arr[j][1] = Integer.parseInt(st.nextToken());
			}
			
			// 서류심사 성적 기준으로 오름차순 정렬
			Arrays.sort(arr, new Comparator<int[]>() {
				public int compare(int[] a, int[] b) {
					return a[0] - b[0];
				}
			});
			
			int cnt = 1; // 서류와 면접 중 하나만 다른 사람보다 높아도 통과이므로 서류 점수가 가장 높은 사람은 무조건 통과
			int min = arr[0][1];
			for(int j = 1; j < N; j++) {
				if(arr[j][1] < min) { // 면접 점수 최소값 갱신
					cnt++;
					min = arr[j][1];
				}
			}
			
			System.out.println(cnt);
		}
	}
}