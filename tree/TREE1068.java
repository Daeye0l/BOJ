package tree;

import java.io.*;
import java.util.*;

public class TREE1068 {
	static List<List<Integer>> tree;
	static boolean[] deletedNodes;
	static int leaf;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList<>();
		deletedNodes = new boolean[N];
		int rootNumber = 0;
		
		for (int i = 0; i < N; i++) {
			tree.add(new ArrayList<>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int parentNumber = Integer.parseInt(st.nextToken());
			if(parentNumber != -1) {
				tree.get(parentNumber).add(i);
			}
			else rootNumber = i;
		}
		
		int deleteNumber = Integer.parseInt(br.readLine());
		delete(deleteNumber);
		dfs(rootNumber);
		System.out.println(leaf);
		
	}
	
	private static void delete(int n) {
		// 입력된 노드 번호에 대하여 자식 노드를 재귀적으로 삭제
		deletedNodes[n] = true;
		for(int childNumber : tree.get(n)) {
			if(!deletedNodes[childNumber]) {
				delete(childNumber);
			}
		}
	}
	
	private static void dfs(int n) {
		int cnt = 0;
		if(deletedNodes[n]) return;
		// 삭제되지 않은 노드에 대하여 자식노드를 탐색
		for(int childNumber : tree.get(n)) {
			if(!deletedNodes[childNumber]) {
				dfs(childNumber);
				cnt++;
			}
		}
		// 자식이 삭제되었거나 없는 경우 리프노드 개수 증가
		if(!deletedNodes[n] && cnt == 0) {
			leaf++;
		}
	}
}