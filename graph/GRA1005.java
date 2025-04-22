package graph;

import java.io.*;
import java.util.*;

public class GRA1005 {
	static int n;
	static List<List<Integer>> graph;
	static int[] cost, inDegree, finish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while(t --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// 각 건물당 걸리는 시간을 저장
			cost = new int[n+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				cost[i] = Integer.parseInt(st.nextToken());
			}
			
			// 각 건물의 연결 관계와 진입 차수를 저장
			graph = new ArrayList<>();
			inDegree = new int[n+1];
			for(int i = 0; i <= n; i++) graph.add(new ArrayList<>());
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.get(x).add(y);
				inDegree[y]++;
			}
			
			int w = Integer.parseInt(br.readLine()); // 승리하기 위해 건설해야 할 건물의 번호
			sb.append(topologicalSort(w)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int topologicalSort(int w) {
		Queue<Integer> que = new LinkedList<>();
		finish = new int[n+1]; // 각 건물이 지어지는데 걸리는 최소 시간을 저장
		
		// 건물들 중 진입차수가 0인 건물들을 큐에 삽입
		for(int i = 1; i <= n; i++) {
			if(inDegree[i] == 0) {
				que.offer(i);
				finish[i] = cost[i];
			}
		}
		
		while(!que.isEmpty()) {
			int curr = que.poll(); // 큐에서 건물 하나를 poll
			// 다음으로 지을 건물을 탐색
			for(int next : graph.get(curr)) {
				// next로 진입되는 건물들 중 시간이 가장 오래 걸리는 건물이 지어지고 나서야 next를 지을 수 있음
				finish[next] = Math.max(finish[next], finish[curr] + cost[next]);
				inDegree[next]--;
				// 진입 차수가 0이라면 큐에 삽입 
				if(inDegree[next] == 0) {
					que.offer(next);
				}
			}
		}
		
		return finish[w];
	}
}