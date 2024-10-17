package graph;

import java.io.*;
import java.util.*;

public class GRA1916 {
	static int N, M;
	static List<List<City>> graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList<>();
		
		for(int i = 0; i <= N; i++) graph.add(new ArrayList<City>());
		// 1 ~ N번 도시의 연결관계 저장
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(s).add(new City(e, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()); // 출발 도시
		int end = Integer.parseInt(st.nextToken()); // 도착 도시
		
		int[] distance = dijkstra(start, end);
		System.out.println(distance[end]);
	}
	
	private static int[] dijkstra(int start, int end) {
		// 출발 도시에서 각 도시까지 도착하는 비용이 적은 순서로 정렬
		Queue<City> que = new PriorityQueue<>(new Comparator<City>() {
			public int compare(City c1, City c2) {
				return c1.cost - c2.cost;
			}
		});
		int[] distance = new int[N+1]; // 시작 도시부터 각 도시까지의 최소 비용을 저장
		boolean[] visited = new boolean[N+1]; // 각 도시의 방문 여부 저장
		for(int i = 1; i <= N; i++) distance[i] = Integer.MAX_VALUE;
		
		distance[start] = 0; // 출발 도시에서 자기 자신까지의 거리는 0
		que.add(new City(start, 0)); // 우선순위 큐에 추가
		while(!que.isEmpty()) {
			City curr = que.poll();
			if(visited[curr.num]) continue;
			visited[curr.num]= true; 
			for(City next : graph.get(curr.num)) {
				// 다음 도시가 방문하지 않은 도시이면서 현재 도시를 거쳐 가는 것이 더 적은 비용이 든다면
				if(!visited[next.num] && curr.cost + next.cost < distance[next.num]) {
					distance[next.num] = curr.cost + next.cost; // 비용 갱신
					que.add(new City(next.num, distance[next.num])); // 갱신된 도시 우선순위 큐에 추가
				}
			}
		}
		
		return distance;
	}
	
	private static class City {
		// 인접 도시에서 num 도시로 오는 비용이 cost
		private int num;
		private int cost;
		
		City(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}
}