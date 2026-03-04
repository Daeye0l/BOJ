package graph;

import java.io.*;
import java.util.*;

public class GRA1446 {
	static List<int[]> inputs = new ArrayList<>();
	static Set<Integer> set = new HashSet<>();
	static List<List<Node>> connections = new ArrayList<>();
	static int INF = 10001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		// 시작점과 종점 추가
		set.add(0);
		set.add(D);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			set.add(start);
			set.add(end);
			
			// 입력은 따로 저장
			inputs.add(new int[] {start, end, cost});
		}
		
		// 각 point에 index(0 ~) 부여
		List<Integer> points = new ArrayList<>(set);
		Collections.sort(points);
		Map<Integer, Integer> map = setIndex(points);
		
		for(int i = 0; i < points.size(); i++) {
			connections.add(new ArrayList<>());
		}
		
		// 지름길 연결 추가
		for(int[] input : inputs) {
			int start = input[0];
			int end = input[1];
			int cost = input[2];
			
			if(end > D || end - start < cost) continue;
			
			connections.get(map.get(start)).add(new Node(map.get(end), cost));
		}
		
		// 일반 연결 추가
		for(int i = 0; i < points.size()-1; i++) {
			int start = points.get(i);
			int end = points.get(i+1);
			
			connections.get(map.get(start)).add(new Node(map.get(end), end-start));
		}
		
		int[] distance = dijkstra(points.size(), 0);
		
		System.out.println(distance[map.get(D)]);
	}
	
	private static int[] dijkstra(int n, int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<>() {
			public int compare(Node n1, Node n2) {
				return n1.cost - n2.cost;
			}
		});
		int[] distance = new int[n];
		for(int i = 0; i < n; i++) {
			distance[i] = INF;
		}
		boolean[] visited = new boolean[n];
		
		distance[start] = 0;
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			
			if(visited[curr.num]) continue;
			visited[curr.num] = true;
			
			for(Node next : connections.get(curr.num)) {
				if(distance[next.num] > distance[curr.num] + next.cost) {
					distance[next.num] = distance[curr.num] + next.cost;
					pq.offer(new Node(next.num, distance[next.num]));
				}
			}
		}
		
		return distance;
	}
	
	private static Map<Integer, Integer> setIndex(List<Integer> points) {
		Map<Integer, Integer> map = new HashMap<>();
		
		int idx = 0;
		for(int point : points) {
			map.put(point, idx++);
		}
		
		return map;
	}
	
	private static class Node {
		private int num;
		private int cost;
		
		Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
}