package algorithmStudy;

import java.io.*;

public class SORT2587 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[5]; // 5개의 자연수를 담을 배열
		int sum = 0; // 자연수의 합을 저장할 변수
		int mid = 0; // 중앙값을 저장할 변수
		
		// 5개의 자연수를 입력받아 배열에 저장하고 합을 누적함
		for(int i = 0; i < 5; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		// 배열의 값을 정렬함
		arr = sort(arr);
		
		// 평균과 중앙값을 출력함
		System.out.println(sum / 5);
		System.out.print(arr[2]);
	}
	
	// 버블정렬 메소드
	private static int[] sort(int[] arr) {
		int f = 0;
		int l = arr.length;
		for(int i = l-1; i > 0; i--) {
			for(int j = 0; j < i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}
}