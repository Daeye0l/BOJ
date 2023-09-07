package greedy;

import java.io.*;
import java.util.*;

public class GR11399 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 사람 수 입력받기
		int[] time = new int[n]; // 돈을 인출하는데 필요한 시간을 저장할 배열 생성
		int[] tmp = new int[n];
		
		// 시간을 입력받아 배열에 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}
		
		// 돈을 인출하는데 필요한 시간의 합이 최소가 되려면 인출시간이 작은 사람부터 돈을 인출해야 한다.
		merge_sort(time, tmp, 0, time.length - 1); // 배열을 오름차순으로 정렬
		
		int sum = time[0];
		// 각 사람이 돈을 인출하는데 필요한 총 시간으로 값을 변경
		for(int i = 1; i < time.length; i++) {
			time[i] = time[i-1] + time[i];
			sum += time[i];
		}
		
		System.out.print(sum);
	}
	
	// 병합정렬 연습
	private static void merge_sort(int[] arr, int[] tmp, int f, int l) {
		if(f < l) {
			int m = (f + l) / 2;
			merge_sort(arr, tmp, f, m);
			merge_sort(arr, tmp, m + 1, l);
			merge(arr, tmp, f, m, l);
		}
	}
	private static void merge(int[] arr, int[] tmp, int f, int m, int l) {
		int i = f;
		int j = m + 1;
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
		
		if(i > m) {
			for(int n = j; n <= l; n++) {
				tmp[k] = arr[n];
				k++;
			}
		}
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