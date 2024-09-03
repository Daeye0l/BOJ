package recursion;

import java.io.*;

public class RECUR11729 {
	static StringBuilder sb;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		sb = new StringBuilder();
		moveDisk(n, 1, 2, 3);
		System.out.println(cnt);
		System.out.println(sb);
		
	}
	
	// n개의 원반을 start에서 temp를 보조로 사용하여 end로 옮김
	private static void moveDisk(int n, int start, int temp, int end) {
		// 원반이 하나 남았다면 start에 있던 원반을 end로 옮김
		if(n == 1) {
			sb.append(start + " " + end).append("\n");
			cnt++;
			return;
		}
		// start에 있던 n-1개의 원반을 end를 보조로 사용하여 temp로 옮김
		moveDisk(n-1, start, end, temp);
		sb.append(start + " " + end).append("\n");
		cnt++;
		// temp에 있던 n-1개의 원반을 start를 보조로 사용하여 end로 옮김 
		moveDisk(n-1, temp, start, end);
	}
}