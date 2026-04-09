package graph;

import java.io.*;
import java.util.*;

public class GRA21276 {
	static List<String> names = new ArrayList<>();
	static Map<String, List<String>> graph = new HashMap<>();
	static Map<String, Integer> inDegree = new HashMap<>();
	static Map<String, List<String>> childs = new HashMap<>();
	static int rootCnt = 0;
	static StringBuilder result = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			String name = st.nextToken();
			names.add(name);
			graph.put(name, new ArrayList<>());
			inDegree.put(name, 0);
			childs.put(name, new ArrayList<>());
		}
		Collections.sort(names);
		
		// X의 조상이 Y이므로 Y에서 X로 가는 간선 추가
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String X = st.nextToken();
			String Y = st.nextToken();
			graph.get(Y).add(X);
			inDegree.put(X, inDegree.get(X)+1);
		}
		
		topologicalSort();
		result.append('\n');
		
		for(String name : names) {
			Collections.sort(childs.get(name));
			StringBuilder temp = new StringBuilder();
			temp.append(name).append(' ').append(childs.get(name).size()).append(' ');
			for(String child : childs.get(name)) {
				temp.append(child + ' ');
			}
			result.append(temp).append('\n');
		}
		
		System.out.println(rootCnt);
		System.out.println(result);
	}
	
	private static void topologicalSort() {
		Queue<String> queue = new LinkedList<>();
		for(String name : names) {
			if(inDegree.get(name) == 0) {
				rootCnt++;
				queue.offer(name);
				result.append(name + ' ');
			}
		}
		
		while(!queue.isEmpty()) {
			String curr = queue.poll();
			for(String next : graph.get(curr)) {
				inDegree.put(next, inDegree.get(next)-1);
				if(inDegree.get(next) == 0) {
					childs.get(curr).add(next);
					queue.offer(next);
				}
			}
		}
	}
}