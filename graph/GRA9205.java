package graph;

import java.io.*;
import java.util.*;

public class GRA9205 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			List<int[]> coordinates = new ArrayList<>();
			int n = Integer.parseInt(br.readLine());
	
			// n+2개의 좌표를 입력 받아 리스트에 저장
			for(int j = 0; j < n+2; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				coordinates.add(new int[] {x, y});
			}
			
			boolean isPossible = bfs(coordinates);
			if(isPossible) System.out.println("happy");
			else System.out.println("sad");
		}
	}
	
	private static boolean bfs(List<int[]> coordinates) {
		Queue<Node> que = new LinkedList<>();
		// 각 좌표의 방문 여부를 저장
		boolean[] visited = new boolean[coordinates.size()];
		
		int[] home = coordinates.get(0);
		int[] festival = coordinates.get(coordinates.size()-1);
		
		visited[0] = true; // 집 방문 체크
		que.offer(new Node(home[0], home[1])); // 큐에 삽입
		while(!que.isEmpty()) {
			Node curr = que.poll();
			// 페스티벌에 도착했다면 true 반환
			if(curr.x == festival[0] && curr.y == festival[1]) return true;
			
			for(int i = 1; i < coordinates.size(); i++) {
				int[] store = coordinates.get(i);
				// 현재 좌표에서 다음 편의점까지 갈 수 있다면 방문 후 큐에 삽입
				if(!visited[i] && Math.abs(curr.x-store[0]) + Math.abs(curr.y-store[1]) <= 1000) {
					visited[i] = true;
					que.offer(new Node(store[0], store[1]));
				}
			}
		}
		
		return false;
	}
	
	private static class Node {
		private int x;
		private int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}