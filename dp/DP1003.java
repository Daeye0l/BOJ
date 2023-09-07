package dp;

import java.io.*;

public class DP1003 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = Integer.parseInt(br.readLine()); // 테스트케이스 수 입력받기
		
		// fib(n)에서 fib(0)과 fib(1)이 호출된 횟수를 저장할 배열
		int[][] arr = new int[2][41];
		
		arr[0][0] = 1; // fib(0)에서 fib(0)이 호출된 횟수 
		arr[1][0] = 0; // fib(0)에서 fib(1)이 호출된 횟수
		arr[0][1] = 0; // fib(1)에서 fib(0)이 호출된 횟수 
		arr[1][1] = 1;// fib(1)에서 fib(1)이 호출된 횟수 
		for(int i = 0; i <= 1; i++) {
			for(int j = 2; j <= 40; j++) {
				// fib(j (2~40))일때 fib(i (0~1))이 호출된 횟수 채우기
				arr[i][j] = arr[i][j-1] + arr[i][j-2]; 
			}
		}
		
		for(int i = 0; i < c; i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(arr[0][n] + " " + arr[1][n]);
		}
	}
}