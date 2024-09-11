package backTracking;

import java.io.*;
import java.util.*;

public class BT13023 {
	static int n, m;
	static List<Person> persons;
	static boolean[] visited;
	static boolean possible;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		persons = new ArrayList<>();
		visited = new boolean[n];
		
		// n명의 사람 생성
		for(int i = 0; i < n; i++) {
			persons.add(new Person(i));
		}
		
		// m개의 관계를 입력 받아서 서로 연관
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Person p1 = persons.get(Integer.parseInt(st.nextToken()));
			Person p2 = persons.get(Integer.parseInt(st.nextToken()));
			if(!p1.friends.contains(p2)) p1.friends.add(p2);
			if(!p2.friends.contains(p1)) p2.friends.add(p1);
		}
		
		// 모든 사람에 대해서 dfs, 가능한 경우를 발견했으면 종료
		for(int i = 0; i < n; i++) {
			if(possible) break;
			dfs(persons.get(i), 0);
		}
		
		if(possible) System.out.println(1);
		else System.out.println(0);
	}
	
	private static void dfs(Person person, int depth) {
		if(depth == 4) {
			possible = true;
			return;
		}
		// 전달받은 사람을 방문 처리
		visited[person.num] = true;
		// 해당 사람의 친구들을 확인하면서
		for(Person friend : person.friends) {
			// 방문하지 않은 사람이 있다면
			if(!visited[friend.num]) {
				// 그 사람으로 재귀
				dfs(friend, depth+1);
			}
		}
		visited[person.num] = false;
	}
	
	static class Person {
		private int num;
		private List<Person> friends;
		
		Person(int num) {
			this.num = num;
			this.friends = new ArrayList<Person>();
		}
	}
 }