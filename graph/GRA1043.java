package graph;

import java.io.*;
import java.util.*;

public class GRA1043 {
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 사람 수
		int m = Integer.parseInt(st.nextToken()); // 파티 수
		
		// 모든 사람의 부모 정보를 자기 자신으로 초기화
		parent = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		// 진실을 아는 사람들로 구성된 리스트 생성
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<>();
		for(int i = 0; i < cnt; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		// m개의 파티 정보를 party 리스트에 저장
		List<List<Integer>> party = new ArrayList<>();
		for(int i = 0; i < m; i++) party.add(new ArrayList<>());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			cnt = Integer.parseInt(st.nextToken());
			for(int j = 0; j < cnt; j++) {
				party.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		// m개의 파티를 순회 하면서 파티에 참가한 사람들의 집합을 구성
		for(int i = 0; i < m; i++) {
			List<Integer> currParty = party.get(i);
			int first = currParty.get(0);
			for(int j = 1; j < currParty.size(); j++) {
				union(first, currParty.get(j));
			}
		}
		
		int result = m; // 거짓말을 할 수 있는 파티의 갯수
		for(int i = 0; i < m; i++) {
			List<Integer> currParty = party.get(i);
			int first = currParty.get(0);
			for(int j = 0; j < list.size(); j++) {
				// 파티에 진실을 아는 사람이 속해 있는 경우
				if(findRoot(first) == findRoot(list.get(j))) {
					result--;
					break;
				}
			}
		}
		
		System.out.println(result);
	}
	
	private static int findRoot(int x) {
		if(parent[x] == x) { // 루트노드인 경우
			return x; // 루트노드 반환
		}
		parent[x] = findRoot(parent[x]); // 현재 노드의 부모르 부모의 루트노드로 변경(경로 압축)   
		return parent[x];
	}
	
	private static boolean union(int x, int y) {
		int xRoot = findRoot(x);
		int yRoot = findRoot(y);
		// 두 노드가 서로 다른 집합에 속해 있다면 병합
		if(xRoot != yRoot) {
			parent[yRoot] = xRoot;
			return true;
		}
		return false;
	}
}