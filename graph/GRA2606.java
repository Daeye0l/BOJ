package graph;

import java.io.*;
import java.util.*;

public class GRA2606 {
	static Computer[] computers; // 컴퓨터 객체를 저장할 배열
	static int infected = 0; // 바이러스에 감염된 컴퓨터의 수를 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		int M = Integer.parseInt(br.readLine()); // 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍
		
		// 컴퓨터 개수만큼 객체 생성 후 배열에 저장
		computers = new Computer[N+1];
		for(int i = 1; i <= N; i++) {
			computers[i] = new Computer(i);
		}
		
		// 한 쌍의 컴퓨터를 입력 받아서 서로 양방향 연결
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Computer c1 = computers[Integer.parseInt(st.nextToken())];
			Computer c2 = computers[Integer.parseInt(st.nextToken())];
			
			if(!c1.adjacent.contains(c2)) c1.adjacent.add(c2);
			if(!c2.adjacent.contains(c1)) c2.adjacent.add(c1);
		}
		
		DFS(computers[1]);
		System.out.println(infected-1); // 1번 컴퓨터를 제외한 감염된 컴퓨터의 수 
	}
	
	private static void DFS(Computer root) {
		root.visited = true;
		infected += 1;
		for(Computer c : root.adjacent) {
			if(!c.visited) {
				DFS(c);
			}
		}
	}
}

class Computer {
	int num; // 컴퓨터 번호
	boolean visited; // 방문 여부를 저장
	LinkedList<Computer> adjacent = new LinkedList<>(); // 인접한 컴퓨터를 저장할 연결리스트
	
	public Computer(int num) {
		this.num = num;
		this.visited = false;
	}
}