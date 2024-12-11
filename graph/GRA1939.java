package graph;

import java.io.*;
import java.util.*;

public class GRA1939 {
	static int n, start, end;
	static List<List<Node>> connect = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int max = 1000000000;
		
		for(int i = 0; i <= n; i++) connect.add(new ArrayList<>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			connect.get(a).add(new Node(b, c));
			connect.get(b).add(new Node(a, c));	
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		System.out.println(binarySearch(1, max));
	}
	
	private static boolean isPossible(int limit) {
		Queue<Integer> que = new LinkedList<>();
		boolean[] visited = new boolean[n+1];
		
		visited[start] = true;
		que.offer(start);
		while(!que.isEmpty()) {
			int curr = que.poll();
			if(curr == end) return true; // 출발 지점에서 도착 지점에 도달 가능한 경우
			for(Node next : connect.get(curr)) {
				// 인자로 들어온 물품 중량 이상의 중량 제한을 가지는 다리만 큐에 삽입
				if(!visited[next.num] && next.limit >= limit) {
					visited[next.num] = true;
					que.offer(next.num);
				}
			}
		}
		
		return false; // 출발 지점에서 도착 지점에 도달할 수 없는 경우
	}
	
	private static int binarySearch(int left, int right) {
		// 물품 중량의 값에 대하여 이분 탐색
		while(left <= right) {
			int mid = (left + right) / 2;
			// 도착할 수 있는 경우 더 큰 범위에서 탐색
			if(isPossible(mid)) {
				left = mid + 1;
			}
			// 도착할 수 없는 경우 더 작은 범위에서 탐색
			else right = mid - 1;
		}
		return right;
	}
	
	private static class Node {
		private int num;
		private int limit;
		
		Node(int num, int limit) {
			this.num = num;
			this.limit = limit;
		}
	}
}