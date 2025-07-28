package tree;

import java.io.*;
import java.util.*;

public class TREE2250 {
	static int[] position;
	static List<List<Integer>> sameLevelList = new ArrayList<>();
	static int pos = 1;
	static int maxWidth = 0;
	static int maxLevel = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N+1];
		int[] parents = new int[N+1];
		position = new int[N+1];
		
		for(int i = 0; i <= N; i++) {
			nodes[i] = new Node(i);
			parents[i] = i;
			sameLevelList.add(new ArrayList<>());
		}
		
		// 각 노드의 왼쪽 자식과 오른쪽 자식을 저장 
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int leftNum = Integer.parseInt(st.nextToken());
			int rightNum = Integer.parseInt(st.nextToken());
			
			Node node = nodes[num];
			if(leftNum != -1) {
				node.left = nodes[leftNum];
				parents[leftNum] = node.num;
			}
			if(rightNum != -1) {
				node.right = nodes[rightNum];
				parents[rightNum] = node.num;
			}
		}
		
		// 루트 노드의 번호로 전위 순회, 중위 순회 진행
		for(int i = 1; i <= N; i++) {
			if(i == parents[i]) {
				preOrder(nodes[i], 1);
				inOrder(nodes[i]);
				break;
			}
		}
		
		for(int i = 1; i <= N; i++) {
			// i번 레벨에 있는 노드들의 번호
			List<Integer> sameLevel = sameLevelList.get(i);
			if(sameLevel.size() != 0) {
				int firstPos = position[sameLevel.get(0)];
				int lastPos = position[sameLevel.get(sameLevel.size()-1)];
				int width = lastPos-firstPos+1;
				
				// 더 큰 너비가 나오면 너비의 최댓값과 레벨을 갱신
				if(width > maxWidth) {
					maxWidth = width;
					maxLevel = i;
				}
			}
		}
		
		System.out.println(maxLevel + " " + maxWidth);
	}
	
	// 전위 순회를 하면서 같은 레벨에 있는 노드들끼리 저장
	private static void preOrder(Node node, int lev) {
		sameLevelList.get(lev).add(node.num);
		if(node.left != null) preOrder(node.left, lev+1);
		if(node.right != null) preOrder(node.right, lev+1);
	}
	
	// 중위 순회를 하면 각 노드의 위치를 저장
	private static void inOrder(Node node) {
		if(node.left != null) inOrder(node.left);
		position[node.num] = pos++;
		if(node.right != null) inOrder(node.right);
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