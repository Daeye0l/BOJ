package algorithmStudy;

import java.io.*;

public class BRUTE2839 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		
		while(n > 0) {
			if(n % 5 == 0) { // 배달해야 할 무게가 5로 나누어 떨어지는 경우
				cnt += n / 5; // 5킬로그램짜리 봉지로 들고감
				n = 0; // 5로 나누어 떨어지면 더 가져가야 할 무게는 없음
			}
			else { // 5로 나누어 떨어지지 않는 경우 3킬로그램짜리 봉지로 들고감
				n -= 3; 
				cnt++; 
			}
			
			if(n < 0) cnt = -1; // 정확하게 N킬로그램이 되지 않는 경우
		}
		
		System.out.print(cnt);
	}
}