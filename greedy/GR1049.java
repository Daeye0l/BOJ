package greedy;

import java.io.*;
import java.util.*;

public class GR1049 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 끊어진 기타줄의 개수
		int M = Integer.parseInt(st.nextToken()); // 기타줄 브랜드의 개수
		
		int min_pack = 0; // 패키지 중 가 싼 가격을 저장
		int min_each = 0; // 낱개 중 가장 싼 가격을 저장
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int pack = Integer.parseInt(st.nextToken());
			int each = Integer.parseInt(st.nextToken());
			
			if(i == 0) {
				min_pack = pack;
				min_each = each;
			}
			else {
				min_pack = Math.min(min_pack, pack);
				min_each = Math.min(min_each, each);
			}
		} 
		
		// 패키지로 전부 사기 vs 패키지로 사고 남는 만큼 낱개로 사기 vs 낱개로 전부 사기
		System.out.println(Math.min((N / 6 + 1) * min_pack, Math.min(N / 6 * min_pack + N % 6 * min_each, N * min_each)));
	}
}