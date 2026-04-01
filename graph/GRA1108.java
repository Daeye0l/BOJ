package graph;

import java.io.*;
import java.util.*;

public class GRA1108 {
	static int webIdx = 0;
	static Map<String, Integer> webNums = new HashMap<>();
	static Set<Integer> allWebs = new HashSet<>();
	
	static List<List<Integer>> graph, sccGraph;
	static int idx, sccIdx;
	static int[] id, low, sccId, sccIndegree;
	static Stack<Integer> stack;
	static boolean[] onStack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 각 웹사이트에 번호를 부여하고 입력 따로 저장
		List<String> inputs = new ArrayList<>();
		allWebs = new HashSet<>();
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			inputs.add(input);
			
			StringTokenizer st = new StringTokenizer(input);
			int to = getWebNum(st.nextToken());
			allWebs.add(to);
			
			int cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				int from = getWebNum(st.nextToken());
				allWebs.add(from);
			}
		}
		String targetWeb = br.readLine();
		
		// 저장한 입력을 다시 읽어서 그래프 만들기
		makeGraph(inputs);
		
		// DFS로 SCC 구성하기
		dfsInit();
		for(int i = 0; i < allWebs.size(); i++) {
			if(id[i] == -1) dfs(i);
		}
		
		// SCC를 기준으로 DAG 만들기
		makeSccGraph();
		
		// 위상정렬 후 정답 구하기(다른 SCC에서 들어오는 경로가 있는 경우 점수 누적)
		System.out.println(topologicalSort(targetWeb));
		
		
//		// debug
//		System.out.println("---graph---");
//		for(int i = 0; i < allWebs.size(); i++) {
//			System.out.print(i + ": ");
//			for(int next : graph.get(i)) {
//				System.out.print(next + ", ");
//			}
//			System.out.println();
//		}
//		
//		// degug
//		System.out.println("---scc id---");
//		for(int i = 0; i < allWebs.size(); i++) {
//			System.out.println(i + ": " + sccId[i]);
//		}
	}
	
	private static long topologicalSort(String targetWeb) {
		Queue<Integer> queue = new LinkedList<>();
		long[] scores = new long[allWebs.size()];
		
		for(int i = 0; i < sccIdx; i++) {
			if(sccIndegree[i] == 0) queue.offer(i);
		}
		Arrays.fill(scores, 1);
		
		while(!queue.isEmpty()) {
			int curr = queue.poll();
			
			for(int i = 0; i < allWebs.size(); i++) {
				// 현재 SCC 소속 웹사이트만 처리
				if(sccId[i] != curr) continue;
	
				for(int next : graph.get(i)) {
					if(sccId[i] != sccId[next]) {
						scores[next] += scores[i];
					}
 				}
			}
			
			for(int next : sccGraph.get(curr)) {
				sccIndegree[next]--;
				
				if(sccIndegree[next] == 0) {
					queue.offer(next);
				}
			}
		}
		
		return scores[getWebNum(targetWeb)];
	}
	
	private static void makeSccGraph() {
		sccGraph = new ArrayList<>();
		sccIndegree = new int[sccIdx];
		
		for(int i = 0; i < sccIdx; i++) {
			sccGraph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < allWebs.size(); i++) {
			for(int next : graph.get(i)) {
				if(sccId[i] != sccId[next]) {
					sccGraph.get(sccId[i]).add(sccId[next]);
					sccIndegree[sccId[next]]++;
				}
			}
		}
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = idx++;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			if(id[next] == -1) {
				dfs(next);
				low[curr] = Math.min(low[curr], low[next]);
			}
			else if(onStack[next]) {
				low[curr] = Math.min(low[curr], id[next]);
			}
		}
		
		if(id[curr] == low[curr]) {
			while(true) {
				int w = stack.pop();
				onStack[w] = false;
				sccId[w] = sccIdx;
				if(w == curr) break;
			}
			sccIdx++;
		}
	}
	
	private static void dfsInit() {
		idx = sccIdx = 0;
		id = new int[allWebs.size()];
		low = new int[allWebs.size()];
		sccId = new int[allWebs.size()];
		stack = new Stack<>();
		onStack = new boolean[allWebs.size()];
		
		Arrays.fill(id, -1);
		Arrays.fill(low,  -1);
	}
	
	private static void makeGraph(List<String> inputs) {
		graph = new ArrayList<>();
		for(int i = 0; i < allWebs.size(); i++) {
			graph.add(new ArrayList<>());
		}
		
		for(String input : inputs) {
			StringTokenizer st = new StringTokenizer(input);
			int to = getWebNum(st.nextToken());
			
			int cnt = Integer.parseInt(st.nextToken());
			for(int i = 0; i < cnt; i++) {
				int from = getWebNum(st.nextToken());
				graph.get(from).add(to);
			}
		}
	}
	
	private static int getWebNum(String web) {
		if(!webNums.containsKey(web)) webNums.put(web, webIdx++);
		return webNums.get(web);
	}
}