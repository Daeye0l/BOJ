package recursion;

import java.io.*;
import java.util.*;

public class RECUR1074 {
	static int result, cnt = 0;
	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		recursion(0, 0, (int)Math.pow(2, N));
		System.out.println(result);
	}
	
	private static void recursion(int x, int y, int size) {
		// 크기가 1이 되면 확인
		if(size == 1) {
			if(x == r && y == c) result = cnt;
			cnt++;
			return;
		}
		
		// z자 순서로 재귀 수행하기
		int nextSize = size/2;
		// 방문하려고 하는 칸이 몇 사분면에 있는지 파악 후 이전 사분면의 칸들을 더해 최적화
		// 방문하려고 하는 칸이 1사분면에 있는 경우
		if(r < x + nextSize && c < y + nextSize) recursion(x, y, nextSize);
		// 방문하려고 하는 칸이 2사분면에 있는 경우
		else if(r < x + nextSize && c >= y + nextSize) {
			cnt += (int)Math.pow(nextSize, 2);
			recursion(x, y + nextSize, nextSize);
		}
		// 방문하려고 하는 칸이 3사분면에 있는 경우
		else if(r >= x + nextSize && c < y + nextSize) {
			cnt += (int)2 * Math.pow(nextSize, 2);
			recursion(x + nextSize, y, nextSize);
		}
		// 방문하려고 하는 칸이 4사분면에 있는 경우
		else {
			cnt += (int)3 * Math.pow(nextSize, 2);
			recursion(x + nextSize, y + nextSize, nextSize);	
		}		
	}
}