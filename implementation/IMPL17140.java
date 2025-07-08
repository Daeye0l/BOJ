package implementation;

import java.io.*;
import java.util.*;

public class IMPL17140 {
	static int rowSize, colSize;
	static int[][] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		
		A = new int[101][101];
		rowSize = 3;
		colSize = 3;
		for(int i = 1; i <= rowSize; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= colSize; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(time <= 100) {
			if(A[r][c] == k) {
				System.out.println(time);
				return;
			}
			
			if(rowSize >= colSize) R();
			else C();
			time++;
		}
		System.out.println(-1);
	}
	
	private static void R() {
		int newColSize = 0;
		
		for(int i = 1; i <= rowSize; i++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			
			// 각 행에서 0이 아닌 수들과 그 개수를 맵에 저장
			for(int j = 1; j <= colSize; j++) {
				int num = A[i][j];
				if(num != 0) {
					if(!map.containsKey(num)) {
						map.put(num, 1);
					}
					else map.put(num, map.get(num)+1);	
				}
			}
			
			// 숫자들이 등장한 횟수가 오름차순이 되도록 정렬, 등장한 횟수가 같다면 숫자가 오름차순이 되도록 정렬
			List<Integer> keys = new ArrayList<Integer>(map.keySet());
			keys.sort(new Comparator<>() {
				public int compare(Integer a, Integer b) {
					if(map.get(a).equals(map.get(b))) {
						return a-b;
					}
					return map.get(a)-map.get(b);
				}
			});
			
			// 행의 길이가 100을 넘어가는 경우 종료
			int pos = 1;
			for(int key : keys) {
				if(pos > 100) break;
				A[i][pos++] = key;
				if(pos > 100) break;
				A[i][pos++] = map.get(key);
			}
			
			// 나머지 자리는 0으로 채우기
			for(int j = pos; j <= 100; j++) {
				A[i][j] = 0;
			}
			
			// 행 길이의 최댓값 갱신
			newColSize = Math.max(newColSize, pos-1);
		}
		
		colSize = newColSize;
	}
	
	private static void C() {
		int newRowSize = 0;
		
		for(int i = 1; i <= colSize; i++) {
			HashMap<Integer, Integer> map = new HashMap<>();
			
			// 각 열에서 0이 아닌 수들과 그 개수를 맵에 저장
			for(int j = 1; j <= rowSize; j++) {
				int num = A[j][i];
				if(num != 0) {
					if(!map.containsKey(num)) {
						map.put(num, 1);
					}
					else map.put(num, map.get(num)+1);	
				}
			}
			
			// 숫자들이 등장한 횟수가 오름차순이 되도록 정렬, 등장한 횟수가 같다면 숫자가 오름차순이 되도록 정렬
			List<Integer> keys = new ArrayList<Integer>(map.keySet());
			keys.sort(new Comparator<>() {
				public int compare(Integer a, Integer b) {
					if (map.get(a).equals(map.get(b))) {
						return a-b;
					}
					return map.get(a)-map.get(b);
				}
			});
			
			// 열의 길이가 100을 넘어가는 경우 종료
			int pos = 1;
			for (int key : keys) {
				if(pos > 100) break;
				A[pos++][i] = key;
				if(pos > 100) break;
				A[pos++][i] = map.get(key);
			}
			
			// 나머지 자리는 0으로 채우기
			for(int j = pos; j <= 100; j++) {
				A[j][i] = 0;
			}
			
			// 열 길이의 최댓값 갱신
			newRowSize = Math.max(newRowSize, pos-1);
		}
		
		rowSize = newRowSize;
	}
}