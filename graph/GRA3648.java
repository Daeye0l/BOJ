package graph;

import java.io.*;
import java.util.*;

public class GRA3648 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder result = new StringBuilder();
		String input = "";
		while((input = br.readLine()) != null && !input.isEmpty()) {
			StringTokenizer st = new StringTokenizer(input);
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			// init
			graph = new ArrayList<>();
			for(int i = 0; i <= 2*n; i++) {
				graph.add(new ArrayList<>());
			}
			idx = sccIdx = 1;
			id = new int[2*n+1]; // 각 노드의 dfs 방문 순서
			low = new int[2*n+1]; // 각 노드의 low-link
			sccId = new int[2*n+1]; // 각 노드가 속한 scc id
			onStack = new boolean[2*n+1]; // 각 노드가 stack에 있는지 여부
			stack = new Stack<>();
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 심사위원의 표 a, b 중에서 적어도 하나는 결과에 영향을 미쳐야 한다는 것은
				// ㄱa -> b, ㄱb -> a를 만족해야 함
				graph.get(changeNum(-a)).add(changeNum(b));
				graph.get(changeNum(-b)).add(changeNum(a));
			}
			// 또한 상근이는 자신을 무조건 진출하게 하려고함
			graph.get(changeNum(-1)).add(changeNum(1));
			
			for(int i = 1; i <= 2*n; i++) {
				if(id[i] == 0) dfs(i);
			}
			
			// 심사위원들의 제약 조건과 상근이가 진출하는 조건으로 2-SAT 식을 구성했으니 가능한지 여부 확인하기
			boolean isValid = true;
			for(int i = 1; i <= n; i++) {
				int pos = 2*i-1;
				int neg = 2*i;
				if(sccId[pos] == sccId[neg]) {
					isValid = false;
					break;
				}
			}
			if(isValid) result.append("yes").append('\n');
			else result.append("no").append('\n');
		}
		
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = idx++;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			// 아직 방문하지 않은 노드는 자식으로 dfs 후 자식을 통해 재귀적으로 low-link 갱신
			if(id[next] == 0) {
				dfs(next);
				low[curr] = Math.min(low[curr], low[next]);
			}
			// 현재 dfs 경로에서 스택에 이미 있는 노드를 만난 경우 해당 노드의 low-link로 갱신
			else if(onStack[next]) {
				low[curr] = Math.min(low[curr], id[next]);
			}
		}
		
		// 더 이상 거슬러 올라갈 수 없는 경우 하나의 SCC 생성
		if(id[curr] == low[curr]) {
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
			sccIdx++;
		}
	}
	
	private static int changeNum(int num) {
		return num > 0 ? 2*num-1 : 2*(-num);
	}
}