package tree;

import java.io.*;
import java.util.*;

public class TREE4803 {
	static List<List<Integer>> tree;
	static boolean[] visited;
	static int vertexCnt, edgeCnt, treeCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = 1; // 테스트 케이스 수
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 정점의 수
			int m = Integer.parseInt(st.nextToken()); // 간선의 수
			
			if(n == 0 && m == 0) return;
			
			// 각 정점별로 인접한 정점을 저장할 배열 생성
			tree = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				tree.add(new ArrayList<>());
			}
			
			// 정점 사이의 연관관계 설정
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int n1 = Integer.parseInt(st.nextToken());
				int n2 = Integer.parseInt(st.nextToken());
				tree.get(n1).add(n2);
				tree.get(n2).add(n1);
			}
			
			// 방문하지 않은 정점을 기준으로 시작해서 dfs가 끝날 때마다 트리의 개수 증가
			visited = new boolean[n+1];
			for(int i = 1; i <= n; i++) {
				if(!visited[i]) {
					vertexCnt = 0;
					edgeCnt = 0;
					dfs(i);
					// 정점의 개수가 v개라면 간선 개수는 v-1이 되어야 한다는 트리의 조건 사용
					// 양방향 연겨을 했으므로 간선 개수 % 2
					if(edgeCnt / 2 == vertexCnt-1) treeCnt++;
				}
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("Case ").append(t).append(": ");
			if(treeCnt > 1) sb.append("A forest of ").append(treeCnt).append(" trees.");
			else if(treeCnt == 1) sb.append("There is one tree.");
			else if(treeCnt == 0) sb.append("No trees.");
			
			System.out.println(sb);
			
			t += 1;
			treeCnt = 0;
		}
	}
	
	private static void dfs(int n) {
		vertexCnt++; // 탐색으로 들어올 때마다 정점 개수 증가
		edgeCnt += tree.get(n).size(); // 간선 개수는 연결된 다른 노드의 수만큼 증가
		
		visited[n] = true;
		for(int adj : tree.get(n)) {
			if(!visited[adj]) dfs(adj);
		}
	}
}