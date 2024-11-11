package greedy;

import java.io.*;
import java.util.*;

public class GR2212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int[] distance = new int[n-1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 센서의 위치를 오름차순으로 정렬
		Arrays.sort(arr);
		
		// 각 센서 사이의 거리를 저장
		for(int i = 1; i < n; i++) {
			distance[i-1] = arr[i] - arr[i-1];
		}
		
		// 집중국이 관리하는 범위가 최소가 되려면 센서 사이의 거리가 먼 곳에서부터 분할해야 함
		// 센서 사이의 거리를 오름차순 정렬하고 가장 큰 k-1개를 제외
		Arrays.sort(distance);
		int sum = 0;
		for(int i = 0; i < (n-1)-(k-1); i++) {
			sum += distance[i];
		}
		
		System.out.println(sum);
	}
}