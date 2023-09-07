package algorithmStudy;

import java.io.*;

public class SORT10989 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine()); // N 입력받기
		
		int[] arr = new int[n]; // N개의 수를 저장할 배열
		int[] result = new int[n]; // 정렬된 결과를 저장할 배열
		int max = 0; // 입력받은 수 중 최댓값을 저장할 변수
		
		// 각 줄마다 수를 입력받아 arr에 저장
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			// 최댓값 max에 저장
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		
		int[] cnt = new int[max + 1]; // 계수정렬을 위한 배열 cnt 생성
		// 각각의 수의 개수를 저장
		for(int i = 0; i < arr.length; i++) {
			cnt[arr[i]]++;
		}
		// 각각의 수보다 작거나 같은 수의 개수를 저장  
		for(int i = 1; i < cnt.length; i++) {
			cnt[i] = cnt[i-1] + cnt[i];
		}
		// cnt배열을 기준으로 결과배열 result를 채움
		for(int i = arr.length-1; i >= 0; i--) {
			result[cnt[arr[i]] - 1] = arr[i];
			cnt[arr[i]]--;
		}
		
		for(int i : result) {
			bw.write(i + "\n");
		}
		bw.flush();
	}
}