package tree;

import java.io.*;
import java.util.*;

public class TREE1991 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// N개의 노드를 생성하여 배열에 저장
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N];
		for(int i = 0; i < N; i++) {
			nodes[i] = new Node((char)(i+'A'));
		}
		
		// 한 줄씩 입력 받으면서 부모노드와 왼쪽 자식, 오른쪽 자식 노드를 연결
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Node parent = nodes[st.nextToken().charAt(0)-'A'];
			char leftValue = st.nextToken().charAt(0);
			char rightValue = st.nextToken().charAt(0);
			
			if(leftValue != '.') parent.left = nodes[leftValue-'A'];
			if(rightValue != '.') parent.right = nodes[rightValue-'A'];
		}
		 
		preOrder(nodes[0]);
		System.out.println();
		inOrder(nodes[0]);
		System.out.println();
		postOrder(nodes[0]);
	}
	
	private static class Node {
		char value;
		Node left;
		Node right;
		
		public Node(char i) {
			this.value = i;
		}
	}
	
	// 전위 순회
	private static void preOrder(Node node) {
		if(node == null) return;
		System.out.print(node.value);
		preOrder(node.left);
		preOrder(node.right);
	}
	
	// 중위 순회
	private static void inOrder(Node node) {
		if(node == null) return;
		inOrder(node.left);
		System.out.print(node.value);
		inOrder(node.right);
	}
	
	// 후위 순회
	private static void postOrder(Node node) {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.value);
	}
}