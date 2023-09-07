package algorithmStudy;

import java.util.Scanner;

public class MD2501 {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		int k = input.nextInt();
		int cnt = 0;
		
		for(int q = 1; q <= n; q++) {
			if(n % q == 0) {
				cnt++;
			}
			if(cnt == k) {
				System.out.print(q);
				break;
			}
		}
		
		if(cnt < k) {
			System.out.print(0);
		}
	}
}