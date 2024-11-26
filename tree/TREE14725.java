package tree;

import java.io.*;
import java.util.*;

public class TREE14725 {
	static Tree tree;
	static StringBuilder result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		tree = new Tree(); // 입구를 루트로 하는 최초의 트리 생성
		result = new StringBuilder();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			Tree curr = tree; // 현재 트리 지정
			for(int j = 0; j < k; j++) {
				String child = st.nextToken();
				if(!curr.childs.containsKey(child)) { // chid가 현재 트리의 자식으로 포함되어 있지 않은 경우
					curr.childs.put(child, new Tree()); // child를 루트로 하는 새로운 트리를 생성하여 추가
				}
				curr = curr.childs.get(child); // 자식 트리로 이동
			}
		}
		
		print(tree, "");
		System.out.println(result);
	}
	
	private static void print(Tree tree, String ahead) {
		List<String> childs = new ArrayList<>(tree.childs.keySet()); // 현재 트리의 자식 리스트
		Collections.sort(childs); // 사전순 정렬
		// 자식 정보를 결과에 새기고 재귀
		for(String child : childs) {
			result.append(ahead).append(child).append("\n");
			print(tree.childs.get(child), ahead + "--");
		}
	}
	
	private static class Tree {
		private Map<String, Tree> childs;
		
		Tree() {
			this.childs = new HashMap<>();
		}
	}
}