package graph;

import java.io.*;
import java.util.*;

public class GRA9019 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
		
			System.out.println(bfs(a, b));
		}
	}
	
	private static String bfs(int a, int b) {
		Queue<Resister> que = new LinkedList<>();
		boolean[] visited = new boolean[100000];
		
		visited[a] = true;
		que.offer(new Resister(a, ""));
		while(!que.isEmpty()) {
			Resister curr = que.poll();
			if(curr.num == b) return curr.state;
			// 연산 결과가 b와 같지 않다면 다시 DSLR 연산을 해서 큐에 삽입
			int d = calcD(curr.num);
			int s = calcS(curr.num);
			int l = calcL(curr.num);
			int r = calcR(curr.num);
			if(!visited[d]) {
				visited[d] = true;
				que.offer(new Resister(d, curr.state + "D"));
			}
			if(!visited[s]) {
				visited[s] = true;
				que.offer(new Resister(s, curr.state + "S"));
			}
			if(!visited[l]) {
				visited[l] = true;
				que.offer(new Resister(l, curr.state + "L"));
			}
			if(!visited[r]) {
				visited[r] = true;
				que.offer(new Resister(r, curr.state + "R"));
			}
		}
		return null;
	}
	
	// D연산
	private static int calcD(int n) {
		if(2*n > 9999) {
			return 2*n % 10000;
		}
		else return 2*n;
	}
	
	// S연산
	private static int calcS(int n) {
		if(n == 0) {
			return 9999;
		}
		else return n-1;
	}
	
	// L연산
	private static int calcL(int n) {
		int quotient = n / 1000;
		int remainder = n % 1000;
		return remainder * 10 + quotient;
	}
	
	// R연산
	private static int calcR(int n) {
		int quotient = n / 10;
		int remainder = n % 10;
		return remainder * 1000 + quotient;
	}
	
	// 레지스터에 저장된 값과 연산 상태를 저장
	private static class Resister {
		int num;
		String state;
		
		Resister(int num, String state) {
			this.num = num;
			this.state = state;
		}
	}
}