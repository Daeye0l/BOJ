package tree;

import java.io.*;
import java.util.*;

public class TREE4256 {
	static int preorderIdx;
	static int[] preorder, inorder;
	static Map<Integer, Integer> inorderIdxMap = new HashMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			preorder = new int[n];
			inorder = new int[n];
			
			// 전위, 중위 순회 결과를 저장하고, 중위 순회 값들의 인덱스를 맵에 저장
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				preorder[j] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				inorder[j] = Integer.parseInt(st.nextToken());
				inorderIdxMap.put(inorder[j], j);
			}
			
			preorderIdx = 0;
			Node tree = buildTree(0, inorder.length-1);
			postorder(tree);
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static Node buildTree(int left, int right) {
		if(left > right) {
			return null;
		}
		
		int rootValue = preorder[preorderIdx++]; // 전위 순회 결과에서 루트 값 가져오기
		int inorderIdx = getInorderIdx(rootValue); // 루트값이 중위 순회 결과에서 어느 위치에 있는지 인덱스 반환
		
		Node root = new Node(rootValue); // 루트 값으로 Node 생성
		// 루트 값을 기준으로 중위 순회 결과를 둘로 쪼개 재귀 수행
		root.left = buildTree(left, inorderIdx-1);
		root.right = buildTree(inorderIdx+1, right);
		
		return root; // 최종 트리 반환
	}
	
	private static int getInorderIdx(int rootValue) {
		return inorderIdxMap.get(rootValue);
	}
	
	private static void postorder(Node node) {
		if(node.left != null) postorder(node.left);
		if(node.right != null) postorder(node.right);
		sb.append(node.value).append(" ");
	}
	
	private static class Node {
		private int value;
		private Node left;
		private Node right;
		
		Node(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
}