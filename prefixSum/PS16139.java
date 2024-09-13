package prefixSum;

import java.io.*;
import java.util.*;

public class PS16139 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		int len = s.length();
		int q = Integer.parseInt(br.readLine());
		int[][] prefixSum = new int[26][s.length()+1];
		
		// 모든 알파벳 반복
		for(int i = 0; i < 26; i++) {
			char alphabet = (char)('a'+i);
			for(int j = 1; j <= len; j++) {
				// 확인하려는 알파벳이 j번째 위치에 없다면
				if(alphabet != s.charAt(j-1)) {
					// 이전 개수를 그대로 사용
					prefixSum[i][j] = prefixSum[i][j-1];
				}
				// j번째 위치에 있다면
				else {
					// 이전 개수 + 1
					prefixSum[i][j] = prefixSum[i][j-1] + 1;
				}
				
			}
		}
		
		StringTokenizer st;
		for(int i = 0; i < q; i++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int l = Integer.parseInt(st.nextToken())+1;
			int r = Integer.parseInt(st.nextToken())+1;
			bw.write(prefixSum[c-97][r] - prefixSum[c-97][l-1] + "\n");
		}
		
		bw.flush();
		bw.close();
	}
}