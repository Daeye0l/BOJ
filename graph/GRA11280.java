package graph;

import java.io.*;
import java.util.*;

public class GRA11280 {
	static List<List<Integer>> graph;
	static int idx = 1, sccIdx = 1;
	static int[] id, low, sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		init(N);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int negA = -a;
			int negB = -b;
			
			a = changeNum(a);
			b = changeNum(b);
			negA = changeNum(negA);
			negB = changeNum(negB);
			
			// 2-sat의 clause는 모두 and로 연결되어 있기 때문에 모든 clause가 true여야 함
			// (A V B)에서 A가 거짓이면 B는 참이어야 하고 B가 거짓이면 A는 참이어야 하기 때문에
			// ㄱA -> B, ㄱB -> A로 표현할 수 있음
			// 그래프 상에서만 봤을 때 A -> B가 존재하는 것은 A가 참이면 B도 반드시 참이어야 한다는 뜻
			graph.get(negA).add(b);
			graph.get(negB).add(a);
		}
		
		// 타잔 알고리즘을 사용하여 scc 구하기
		for(int i = 1; i <= 2*N; i++) {
			if(id[i] == 0) dfs(i);
		}
		
		for(int i = 1; i <= N; i++) {
			int pos = 2*i-1;
			int neg = 2*(i);
			// X -> ㄱX가 있으면서 동시에 ㄱX -> X가 있다는 것은 논리적으로 모순이 발생
			if(sccId[pos] == sccId[neg]) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}
	
	private static void dfs(int curr) {
		// dfs방문 순서와 거슬러 올라갈 수 있는 최소 id 저장 후 스택에 push
		id[curr] = low[curr] = idx++;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			// 방문하지 않은 인접 노드인 경우
			if(id[next] == 0) {
				// dfs를 한 다음 자식을 통해 더 거슬러 올라갈 수 있으면 갱신
				dfs(next);
				low[curr] = Math.min(low[curr], low[next]);
			}
			// 방문한 인접 노드이면서 스택에 남아 있는 경우
			else if(onStack[next]) {
				// 현재 dfs 경로 상에서 사이클이 형성될 수 있으므로 scc이 후보가 됨
				// low-link 값 갱신
				low[curr] = Math.min(low[curr], id[next]);
			}
		}
		
		// 더 이상 거슬러 올라갈 수 없다면 scc가 완성됨
		if(low[curr] == id[curr]) {
			// 스택에서 가장 상위 정점이 나올때까지 pop하여 scc 구성
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
		num = num > 0 ? 2*num-1 : 2*(-num);
		return num;
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		// 각각의 변수는 양수, 음수 2가지 경우가 있음
		// 양수는 2*n-1으로 저장, 음수는 2*(-n)으로 저장
		for(int i = 0; i <= 2*N; i++) {
			graph.add(new ArrayList<>());
		}
		id = new int[2*N+1];
		low = new int[2*N+1];
		sccId = new int[2*N+1];
		onStack = new boolean[2*N+1];
		stack = new Stack<>();
	}
}