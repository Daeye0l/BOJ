package implementation;

import java.io.*;
import java.util.*;

public class IMPL16235 {
	static int n;
	static int[][] land;
	static int[][] A;
	static int[][] dirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		land = new int[n+1][n+1];
		A = new int[n+1][n+1];
		
		// 땅의 처음 양분과 추가되는 양분 초기화
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= n; j++) {
				land[i][j] = 5;
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 현재 땅에 존재하는 나무(나이 오름차순으로 정렬)
		PriorityQueue<Tree> trees = new PriorityQueue<>(new Comparator<>() {
			public int compare(Tree t1, Tree t2) {
				return t1.age - t2.age;
			}
		});
		
		// 처음 땅에 심은 나무를 저장
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			
			trees.offer(new Tree(x, y, age));
		}
		
		// k년 동안 반복
		for(int i = 0; i < k; i++) {
			List<Tree> deadTrees = spring(trees);
			summer(deadTrees);
			fall(trees);
			winter();
		}
		
		System.out.println(trees.size());
	}
	
	private static List<Tree> spring(PriorityQueue<Tree> trees) {
		// 죽은 나무와 살아남은 나무를 저장할 리스트
		List<Tree> deadTrees = new ArrayList<>();
		List<Tree> aliveTrees = new ArrayList<>();
		
		while(!trees.isEmpty()) {
			Tree tree = trees.poll();
			// 땅의 양분이 나무의 나이 이상이어야 나무가 양분을 먹음
			if(land[tree.x][tree.y] >= tree.age) {
				aliveTrees.add(new Tree(tree.x, tree.y, tree.age+1));
				land[tree.x][tree.y] -= tree.age;
			}
			// 땅의 양분이 나무의 나이보다 작다면 나무가 죽음
			else {
				deadTrees.add(tree);
			}
		}
		
		// 살아남은 나무들을 우선순위 큐에 추가하고 죽은 나무 리스트를 반환
		for(Tree tree : aliveTrees) {
			trees.offer(tree);
		}
		
		return deadTrees;
	}
	
	private static void summer(List<Tree> deadTrees) {
		// 죽은 나무들이 양분으로 변함
		for(Tree tree : deadTrees) {
			land[tree.x][tree.y] += tree.age / 2;
		}
	}
	
	private static void fall(PriorityQueue<Tree> trees) {
		// 번식하여 새로 생기는 나무 리스트
		List<Tree> newTrees = new ArrayList<>();
		
		for(Tree tree : trees) {
			// 나무의 나이가 5의 배수이면서
			if(tree.age % 5 == 0) {
				for(int i = 0; i < dirs.length; i++) {
					int nx = tree.x + dirs[i][0];
					int ny = tree.y + dirs[i][1];
					
					// 땅을 벗어나지 않는 경우 새로 생길 수 있음
					if(nx >= 1 && nx <= n && ny >= 1 && ny <= n) {
						newTrees.add(new Tree(nx, ny, 1));
					}
				}	
			}
		}
		
		// 새로 생긴 나무들을 우선순위 큐에 추가
		for(Tree tree : newTrees) {
			trees.offer(tree);
		}
	}
	
	private static void winter() {
		// S2D2가 땅을 돌아다니면서 땅에 양분을 추가
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				land[i][j] += A[i][j];
			}
		}
	}
	
	private static class Tree {
		private int x;
		private int y;
		private int age;
		
		Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}
}