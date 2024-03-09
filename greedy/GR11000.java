package greedy;

import java.io.*;
import java.util.*;

public class GR11000 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Lesson[] lessons = new Lesson[n]; // 강의들을 저장할 배열
		PriorityQueue<Integer> pq = new PriorityQueue<>(); // 강의들의 종료시간이 들어갈 우선순위 큐
		
		// 강의 생성후 배열에 저장, 강의 시작시간으로 오름차순 정렬
		for(int i = 0 ; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			lessons[i] = new Lesson(s, t);
		}
		Arrays.sort(lessons);
		
		pq.add(lessons[0].t);
		for(int i = 1; i < n; i++) {
			if(pq.peek() <= lessons[i].s) pq.remove(); // 큐에서 가장 작은 종료시간보다 강의의 시작시간이 더 크거나 같다면 큐에서 강의를 빼고
			pq.add(lessons[i].t); // 다음 강의를 배정
		}
		
		System.out.println(pq.size());
	}
	
	private static class Lesson implements Comparable<Lesson> {
		private int s;
		private int t;
		
		public Lesson(int s, int t) {
			this.s = s;
			this.t = t;
		}
		
		@Override
		public int compareTo(Lesson l) {
			if(s == l.s) {
				return t - l.t;
			}
			return s - l.s;
		}
	}
}