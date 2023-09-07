package greedy;

import java.io.*;
import java.util.*;

public class GR1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 회의 수 입력받기
		Meeting[] arr = new Meeting[n]; // 회의를 저장할 배열 생성
		
		// 각 회의의 시작시간과 끝나는 시간을 입력받아 배열에 저장
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			arr[i] = new Meeting(s, e);
		}
		
		/* 회의를 최대로 고르는 경우는 우선 회의들을 끝나는 시간을 기준으로 오름차순으로 정렬하고
		끝나느 시간이 같다면 시작 시간까지 고려하여 오름차순으로 정렬한 후 고를 수 있는 회의들을 선택하는 것이다. */
		Arrays.sort(arr, new Comparator<Meeting>() {
			public int compare(Meeting a, Meeting b) {
				if(a.end == b.end) {
					return a.start - b.start;
				}
				return a.end - b.end;
			}
		});
		
		// 첫 번째 회의를 선택한 상태에서
		Meeting select = arr[0];
		int sum = 1;
		// 두 번째 회의부터 마지막 회의까지 확인하면서 고를 수 있는 회의들을 모두 고름
		for(int i = 1; i < arr.length; i++) {
			if(arr[i].start >= select.end) {
				sum += 1;
				select = arr[i];
			}
		}
		
		System.out.print(sum);
	}
}

// 회의 클래스 생성
class Meeting {
	int start;
	int end;
	
	Meeting(int start, int end) {
		this.start = start;
		this.end = end;
	}	
}