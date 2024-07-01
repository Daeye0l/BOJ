package tree;

import java.io.*;
import java.util.*;

public class TREE9934 {
	static List<Integer> tree;
	static List<List<Integer>> level;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine()); // 트리의 깊이
		tree = new ArrayList<>(); // 입력 받은 빌딩 번호를 순서대로 저장
		level = new ArrayList<>(); // 각 레벨별 빌딩 번호를 저장할 배열
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i < Math.pow(2, k); i++) {
			tree.add(Integer.parseInt(st.nextToken()));
		}
		for(int i = 0; i <= k; i++) {
			level.add(new ArrayList<>());
		}
		
		inOrder(0, tree.size()-1, 1);
		
		for(int i = 1; i <= k; i++) {
			for(int j = 0; j < level.get(i).size(); j++) {
				System.out.print(level.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}
	
	// 빌딩을 방문한 순서가 중위 순회
	private static void inOrder(int low, int high, int lev) {
		if(low <= high) {
			int mid = (low + high) / 2;
			// 레벨을 증가시켜 가면 중간에 있는 빌딩 번호를 저장
			level.get(lev).add(tree.get(mid));
			lev += 1;
			inOrder(low, mid - 1, lev);
			inOrder(mid + 1, high, lev);		
		}
	}
}