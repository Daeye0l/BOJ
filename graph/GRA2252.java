package graph;

import java.io.*;
import java.util.*;

public class GRA2252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] inDegreeCnt = new int[n+1]; // 각 학생 번호의 진입 차수를 저장
		List<List<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			inDegreeCnt[b]++; // b학생의 진입차수 증가
			graph.get(a).add(b); // a학생에서 b학생으로 연결되는 관계 저장
		}
		
		StringBuilder sb = new StringBuilder();
		Queue<Integer> que = new LinkedList<>();
		
		// 위상정렬
		// 진입차수가 0인 학생 번호 큐에 삽입
		for(int i = 1; i <= n; i++) { 
			if(inDegreeCnt[i] == 0) que.offer(i);
		}
		while(!que.isEmpty()) {
			int curr = que.poll();
			sb.append(curr).append(" ");
			// 현재 큐에서 poll한 학생 번호와 연결된 학생 번호의 진입 차수 감소
			for(int num : graph.get(curr)) {
				inDegreeCnt[num]--;
				// 진입 차수가 갱신된 학생 번호 중에 진입 차수가 0이된 경우 큐에 삽입
				if(inDegreeCnt[num] == 0) {
					que.offer(num);
				}
			}
		}
		
		System.out.println(sb);
	}
}