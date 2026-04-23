package graph;

import java.io.*;
import java.util.*;

public class GRA2848 {
	static Map<Character, Set<Character>> graph;
	static int idx;
	static String[] inputs;
	static Map<Character, Integer> indegree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		graph = new HashMap<>();
		idx = 0;
		inputs = new String[N];
		indegree = new HashMap<>();
		
		// 입력을 따로 저장하고 각 알파벳에 대한 간선을 저장할 그래프 생성
		for(int i = 0; i < N; i++) {
			String word = br.readLine();
			inputs[i] = word;
			
			for(int j = 0; j < word.length(); j++) {
				char c = word.charAt(j);
				if(!graph.containsKey(c)) {
					graph.put(c, new HashSet<>());
					indegree.put(c, 0);
				}
			}
		}
		
		// 알고스팟어의 사전순 입력에 대해 그래프 완성하기
		boolean isValid = true;
		for(int i = 0; i < inputs.length-1; i++) {
			int idx1 = 0;
			int idx2 = 0;
			boolean foundDiff = false;
			
			// 입력은 이미 사전순으로 정렬되어 있기 때문에 인접한 단어끼리만 비교하면 됨
			while(idx1 < inputs[i].length() && idx2 < inputs[i+1].length()) {
				char c1;
				char c2;
				// 같은 위치에서 다른 문자가 나오면 간선을 추가할 수 있음 그리고 이미 여기서 사전순 정렬의 의미를 다했으므로 종료
				if((c1 = inputs[i].charAt(idx1)) != (c2 = inputs[i+1].charAt(idx2))) {
					if(!graph.get(c1).contains(c2)) {
						graph.get(c1).add(c2);
						indegree.put(c2, indegree.get(c2)+1);	
					}
					foundDiff = true;
					break;
				}
				else {
					idx1++;
					idx2++;
				}
			}
			
			// 다른 부분을 발견하지 못했는데 앞의 단어가 뒤의 단어보다 길면 사전순이 아님
			if(!foundDiff && inputs[i].length() > inputs[i+1].length()) {
				isValid = false;
				break;
			}
		}
		
		if(isValid) {
			System.out.println(topologicalSort(graph.keySet().size()));	
		}
		else System.out.println("!");
	}
	
	private static String topologicalSort(int n) {
		Queue<Character> queue = new LinkedList<>();
		int visitedCnt = 0;
		boolean twoOrMore = false; // 가능한 경우가 2개 이상인지 체크
		
		for(char c : graph.keySet()) {
			if(indegree.get(c) == 0) {
				queue.offer(c);
			}
		}
		
		StringBuilder result = new StringBuilder();
		while(!queue.isEmpty()) {
			if(queue.size() > 1) twoOrMore = true;
			
			char curr = queue.poll();
			visitedCnt++;
			result.append(curr);
			for(char next : graph.get(curr)) {
				indegree.put(next, indegree.get(next)-1);
				if(indegree.get(next) == 0) {
					queue.offer(next);
				}
			}
		}
		
		if(visitedCnt < n) return "!"; // 방문한 개수가 정점 개수보다 작으면 사이클 존재
		if(twoOrMore) return "?"; // 2개 이상의 결과가 가능한 경우
		return result.toString();
	}
}