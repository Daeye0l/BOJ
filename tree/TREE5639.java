package tree;

import java.io.*;

public class TREE5639 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 후위 순회를 하기 위해 루트노드는 따로 저장
		Node rootNode = new Node(Integer.parseInt(br.readLine()));
		String s = "";
		while((s = br.readLine()) != null && !s.isEmpty()) {
			int n = Integer.parseInt(s);
			Node childNode = new Node(n); 
			insert(rootNode, childNode);
		}
		
		postOrder(rootNode);
	}
	
	private static class Node {
		int value;
		Node leftChild;
		Node rightChild;
		
		public Node(int value) {
			this.value = value;
		}
	}
	
	// 부모노드의 값과 계속 비교해서 더 내려갈 곳이 없다면 해당 위치에 삽입
	private static void insert(Node root, Node child) {
		if(child.value < root.value) {
			if(root.leftChild != null) {
				insert(root.leftChild, child);	
			}
			else {
				root.leftChild = child;	
			}
		}
		else if(child.value > root.value) {
			if(root.rightChild != null) {
				insert(root.rightChild, child);
			}
			else {
				root.rightChild = child;	
			}
		}
	}
	
	private static void postOrder(Node root) {
		if(root == null) return;
		postOrder(root.leftChild);
		postOrder(root.rightChild);
		System.out.println(root.value);
	}
}