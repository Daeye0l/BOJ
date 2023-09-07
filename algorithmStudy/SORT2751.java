package algorithmStudy;

import java.io.*;

public class SORT2751 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); // 수의 개수 N 입력받기
		int[] arr = new int[n]; // N 크기의 배열 생성
		int[] tmp = new int[n]; // 정렬된 값을 저장할 배열
		
		for(int i = 0; i < arr.length; i++) { // N개의 수를 입력받아 배열에 저장
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		mergeSort(arr, tmp, 0, arr.length-1);
		
		// 오름차순으로 출력
		for(int i : arr) {
			bw.write(i + "\n");
		}
		
		bw.flush();
	}
	
	private static void mergeSort(int[] arr, int[] tmp, int f, int l) {
		if(f < l) { // 배열의 원소가 2개 이상부터
			int m = (f + l) / 2; // m은 배열의 중간 인덱스
			mergeSort(arr, tmp, f, m); // 시작 ~ 중간까지 다시 병합정렬
			mergeSort(arr, tmp, m + 1, l); // 중간+1 ~ 끝까지 다시 병합정렬
			merge(arr, tmp, f, m, l); // 병합
		}
	}
	
	private static void merge(int[] arr, int[] tmp, int f, int m, int l) {
		int i = f; // i는 배열의 시작지점
		int j = m + 1; // m은 배열의 중간+1지점 
		int k = f;
		while(i <= m && j <= l) {
			if(arr[i] < arr[j]) {
				tmp[k] = arr[i];
				i++;
			}
			else {
				tmp[k] = arr[j];
				j++;
			}
			k++;
		}
		
		// 반으로 가른 뒷부분이 남는 경우 남은 원소 채워넣기 
		if(i > m) {
			for(int n = j; n <= l; n++) {
				tmp[k] = arr[n];
				k++;
			}
		}
		// 반으로 가른 앞부분이 남는 경우 남은 원소 채워넣기
		else {
			for(int n = i; n <= m; n++) {
				tmp[k] = arr[n];
				k++;
			}
		}
		
		for(int n = f; n <= l; n++) {
			arr[n] = tmp[n];
		}
	}
}