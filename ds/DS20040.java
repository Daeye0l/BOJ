package ds;

import java.io.*;
import java.util.*;

public class DS20040 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parent = new int[n]; // 각 점의 부모를 저장
		
		// 처음 각 점의 부모는 자기 자신
		for(int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		for(int i = 0; i < m; i++) {
			// 두 점 입력 받기
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 두 점이 다른 그룹이라면 한 그룹으로 병합
			if(findRoot(x) != findRoot(y)) {
				union(x, y);
			}
			// 사이클이 생긴 경우 차례 번호 출력 후 종료
			else {
				System.out.println(i+1);
				return;
			}
		}
		System.out.println(0);
	}
	
	// 연결된 점 그룹의 대표 점을 반환
	private static int findRoot(int x) {
		// 어떤 점의 부모가 자기 자신이라면 그 그룹을 대표하는 점임
		if(parent[x] == x) {
			return x;
		}
		else {
			// 그룹의 대표점을 찾아내서 그룹에 있는 모든 점의 부모를 대표 점으로 변경
			int root = findRoot(parent[x]);
			parent[x] = root;
			return root;
		}
	}
	
	// 두 그룹을 병합
	private static void union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
		}
	}
 }