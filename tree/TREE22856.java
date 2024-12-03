package tree;

import java.io.*;
import java.util.*;

public class TREE22856 {
	static List<Integer> inorderResult = new ArrayList<>();
	static int n, lastNode, result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[n+1];
		
		for(int i = 1; i <= n; i++) nodes[i] = new Node(i);
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(b != -1) nodes[a].left = nodes[b];
			if(c != -1) nodes[a].right = nodes[c];
		}
		
		// 중위 순회를 통해 순회의 마지막 노드가 무엇인지 찾기
		inorder(nodes[1]);
		lastNode = inorderResult.get(inorderResult.size()-1);
		// 유사 중위 순회를 시작하고 마지막 노드에 도달하면 횟수 출력 후 종료
		similarInorder(nodes[1]);
	}
	
	private static void similarInorder(Node curr) {
		// 왼쪽 자식노드로 방문하기 전에 카운트 증가, 방문 종료 후 카운트 증가
		if(curr.left != null) {
			result++;
			similarInorder(curr.left);
			result++;
		}
		// 오른쪽 자식노드로 방문하기 전에 카운트 증가, 방문 종료 후 카운트 증가
		if(curr.right != null) {
			result++;
			similarInorder(curr.right);
			result++;
		}
		// 방문한 노드가 마지막 이동 횟수를 출력하고 즉시 종료
		if(curr.num == lastNode) {
			System.out.println(result);
			return;
		}
	}
	
	// 중위순회 결과 리시트에 저장
	private static void inorder(Node curr) {
		if(curr.left != null) inorder(curr.left);
		inorderResult.add(curr.num);
		if(curr.right != null) inorder(curr.right);
	}
	
	private static class Node {
		private int num;
		private Node left;
		private Node right;
		
		Node(int num) {
			this.num = num;
			this.left = null;
			this.right = null;
		}
	}
}