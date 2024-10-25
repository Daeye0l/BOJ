package graph;

import java.io.*;
import java.util.*;

public class GRA1707 {
	static int V, E;
	static List<List<Integer>> graph;
	static int[] color;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();

			for (int j = 0; j <= V; j++)
				graph.add(new ArrayList<>());
			for (int j = 0; j < E; j++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				// 그래프 양방향 연결
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			color = new int[V + 1];
			// 그래프의 성분이 여러개 있을 수 있기 때문에 모든 정점에 대해 확인
			boolean result = true;
			for(int j = 1; j <= V; j++) {
				if(color[j] == 0 && !bfs(j)) {
					result = false;
					break;
				}
			}
			
			if(result) System.out.println("YES");
			else System.out.println("NO");
			graph.clear();
		}

	}

	private static boolean bfs(int start) {
		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i <= V; i++) {
			color[i] = 0;
		}

		color[start] = 1; // 시작 정점 1번 색으로 칠하기
		que.offer(start); // 큐에 삽입
		while (!que.isEmpty()) {
			int curr = que.poll();
			for (int adjacent : graph.get(curr)) {
				// 인접한 정점들을 방문하면서 아직 색이 칠해지지 않았다면 반대 색상으로 칠하기
				if (color[adjacent] == 0) {
					color[adjacent] = -color[curr];
					que.offer(adjacent);
				}
				// 이미 자신과 같은 색상이라면 이분 그래프가 이님
				else if (color[adjacent] == color[curr]) {
					return false;
				}
			}

		}
		
		return true;
	}
}