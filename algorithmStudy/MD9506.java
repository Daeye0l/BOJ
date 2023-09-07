package algorithmStudy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MD9506 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> arr = new ArrayList<>(); // n의 약수를 저장할 리스트
		
		while(true) {
			arr.clear(); // 리스트 초기화
			int sum = 0; // 약수의 합을 저장할 변수
			int n = Integer.parseInt(br.readLine()); // n을 입력받고 
			if(n == -1) { // n이 -1이라면 종료
				return;
			}
			else {
				for(int i = 1; i < n; i++) {
					if(n % i == 0) { // n의 약수라면
						sum += i; // 약수의 합을 수정하고
						arr.add(i); // 리스트에 추가
					}
				}
				
				if(sum == n) { // 약수의 합과 n이 같은 경우
					System.out.print(n + " = ");
					for(int i = 0; i < arr.size(); i++) {
						if(i == arr.size()-1) {
							System.out.print(arr.get(i));
						}
						else {
							System.out.print(arr.get(i) + " + ");
						}
					}
				}
				
				else { // 약수의 합과 n이 같지 않은 경우
					System.out.print(n + " is NOT perfect.");
				}
				System.out.println();
			}
		}
	}
}