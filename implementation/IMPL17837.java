package implementation;

import java.io.*;
import java.util.*;

public class IMPL17837 {
	static int N, K;
	static int[][] dirs = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 체스판의 정보 저장
		int[][] info = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				info[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 말들이 쌓인 상태를 저장할 배열
		Deque<Integer>[][] state = new ArrayDeque[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				state[i][j] = new ArrayDeque<>();
			}
		}
		
		// 말들의 정보 저장
		int[][] pieces = new int[K][3];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken())-1;
			
			pieces[i] = new int[] {r, c, d};
			state[r][c].offerLast(i);
		}
		
		System.out.println(getTurn(pieces, info, state));
	}
	
	private static int getTurn(int[][] pieces, int[][] info, Deque<Integer>[][] state) {
		int turn = 1;
		
		while(turn <= 1000) {
			// 모든 말을 순서대로 탐색
			for(int i = 0; i < K; i++) {
				int[] piece = pieces[i];
				
				// 현재 말과 같이 있는 말들을 가져와서
				Deque<Integer> group = state[piece[0]][piece[1]];
				
				// 이동할 말들을 따로 분리
				Deque<Integer> movingGroup = new ArrayDeque<>();
				while(true) {
					int num = group.pollLast();
					movingGroup.offerFirst(num);
					if(num == i) break;
				}
				
				// 다음 위치 계산
				int nd = piece[2];
				int nr = piece[0] + dirs[nd][0];
				int nc = piece[1] + dirs[nd][1];
				
				// 다음 칸이 파란색 칸인 경우
				if(nr < 0 || nr >= N || nc < 0 || nc >= N || info[nr][nc] == 2) {
					// 방향을 바꿔서 다음 위치 계산
					if(piece[2] == 0) nd = 1;
					else if(piece[2] == 1) nd = 0;
					else if(piece[2] == 2) nd = 3;
					else nd = 2;
					piece[2] = nd;
					nr = piece[0] + dirs[nd][0];
					nc = piece[1] + dirs[nd][1];
					
					// 이동하려는 칸이 또 파란색 칸이라면 이동하지 않음
					if(nr < 0 || nr >= N || nc < 0 || nc >= N || info[nr][nc] == 2) {
						while(!movingGroup.isEmpty()) {
							group.offerLast(movingGroup.pollFirst());
						}
						continue;
					}
				}
				
				// 다음 칸이 빨간색인 경우
				if(info[nr][nc] == 1) {
					// 이동하는 말들을 뒤집음
					List<Integer> list = new ArrayList<>(movingGroup);
					Collections.reverse(list);
					movingGroup = new ArrayDeque<>(list);
				}
				
				// 이동
				piece[2] = nd;
				while(!movingGroup.isEmpty()) {
					int num = movingGroup.pollFirst();
					pieces[num][0] = nr;
					pieces[num][1] = nc;
					state[nr][nc].offerLast(num);
				}
				
				// 말이 4개 이상 쌓였다면 턴수 반환
				if(state[nr][nc].size() >= 4) {
					return turn;
				}
			}
			turn++;
		}
		return -1;
	}
}