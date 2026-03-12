package graph;

import java.io.*;
import java.util.*;

public class GRA10282 {
	static List<List<Computer>> graph;
	static int[] distance;
	static boolean[] visited;
	static int INF = 10000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			init(n);
			
			for(int j = 0; j < d; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				graph.get(b).add(new Computer(a, s));
			}
			
			int[] result = dijkstra(n, c);
			System.out.println(result[0] + " " + result[1]);
		}
	}
	
	private static int[] dijkstra(int n, int start) {
		PriorityQueue<Computer> pqueue = new PriorityQueue<>(new Comparator<>() {
			public int compare(Computer c1, Computer c2) {
				return c1.time - c2.time;
			}
		});
		
		distance[start] = 0;
		pqueue.offer(new Computer(start, 0));
		
		while(!pqueue.isEmpty()) {
			Computer curr = pqueue.poll();
			
			if(visited[curr.num]) continue;
			visited[curr.num] = true;
			
			for(Computer next : graph.get(curr.num)) {
				if(distance[curr.num] + next.time < distance[next.num]) {
					distance[next.num] = distance[curr.num] + next.time;
					pqueue.offer(new Computer(next.num, distance[next.num]));
				}
			}
		}
		
		int cnt = 0;
		int time = 0;
		int[] result;
		for(int i = 1; i <= n; i++) {
			if(distance[i] != INF) {
				cnt++;
				time = Math.max(time, distance[i]);
			}
		}
		
		result = new int[] {cnt, time};
		return result;
	}
	
	private static void init(int n) {
		graph = new ArrayList<>();
		distance = new int[n+1];
		visited = new boolean[n+1];
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
			distance[i] = INF;
		}
	}
	
	private static class Computer {
		private int num;
		private int time;
		
		Computer(int num, int time) {
			this.num = num;
			this.time = time;
		}
	}
}