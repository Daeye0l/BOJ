package greedy;

import java.io.*;

public class GR1789 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long s = Long.parseLong(br.readLine()); // S 입력받기
		
		// 자연수의 개수가 최대가 되려면 가장 작은 자연수부터 시작해서 최대한 많이 더해야 하므로
		// 1, 2, 3...순서로 계속 더해 S가 넘어서는 시점에서 개수를 하나 뺀다.
		long sum = 0;
		int i = 0;
		while(sum <= s) {
			if(sum > s) break;
			i++;
			sum += i;
		}
		
		// 1 + 2 + 3 + ... + 20 - 10 = 200;
		System.out.print(i-1);
	}
}