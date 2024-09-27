package backTracking;

import java.io.*;
import java.util.*;

public class BT15666 {
	static int n, m;
	static List<Integer> list;
	static int[] temp;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		temp = new int[m];
		sb = new StringBuilder();
		
		// 중복되는 수는 제외하고 수열에 추가
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!list.contains(num)) {
				list.add(num);
			}
		}
		// 사전순 출력을 위해 정렬
		Collections.sort(list);
		
		recur(0, 0);
		System.out.println(sb);
	}
	
	
	private static void recur(int idx, int depth) {
		// 수를 m개 선택하고 나면 결과에 추가
		if(depth == m) {
			for(int num : temp) {
				sb.append(num + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 수의 중복 사용은 허용, 재귀가 끝나고 나면 인덱스 증가
		for(int i = idx; i < list.size(); i++) {
			temp[depth] = list.get(i);
			recur(idx, depth + 1);
			idx++;
		}
	}
}