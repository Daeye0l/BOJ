package tree;

import java.io.*;
import java.util.*;

public class TREE2263 {
	static int[] inOrderList, postOrderList, inOrderIdx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		inOrderList = new int[n];
		postOrderList = new int[n];
		inOrderIdx = new int[100001];
		
		// 전위순회 결과와 각 정점의 인덱들를 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			inOrderList[i] = Integer.parseInt(st.nextToken());
			inOrderIdx[inOrderList[i]] = i;
		}
		
		// 후위순회 결과 저장
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			postOrderList[i] = Integer.parseInt(st.nextToken());
		}
		
		devide(0, n-1, 0, n-1);
	}
	
	private static void devide(int is, int ie, int ps, int pe) {
		if(is > ie || ps > pe) return;
		// 후위순회 결과에서 rootValue를 찾고 중위순회 결과를 기준으로 rootIdx와 leftLength, rightLength 구하기
		int rootValue = postOrderList[pe];
		int rootIdx = inOrderIdx[rootValue];
		int leftLength = rootIdx-is;
		int rightLength = ie-rootIdx;
		
		System.out.print(rootValue + " ");
		devide(is, rootIdx-1, ps, ps+leftLength-1);
		devide(rootIdx+1, ie, pe-rightLength, pe-1);
	}
}