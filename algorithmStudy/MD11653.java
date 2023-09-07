package algorithmStudy;

import java.io.*;

public class MD11653 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		/* 처음에 소수인 2와 3으로 나누어 떨어지게 되면 2의 배수와 3의 배수는 어차피
		인수가 되지 못한다. 그러므로 다음으로도 소수인 5로 나누게 된다. 소수를 찾는 과정이
		없어도 풀 수 있다. */
		for(int i = 2; i <= n; i++) {
			while(n % i == 0) {
				System.out.println(i);
				n /= i;
			}
		}
	}
}