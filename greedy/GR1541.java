package greedy;

import java.io.*;

public class GR1541 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String[] arr = s.split("-"); // 문자열을 -를 기준으로 분리해서 배열에 저장한다.
		
		int result = 0;
		for(int i = 0; i < arr.length; i++) {
			int sum = 0;
			
			if(arr[i].contains("+")) { // 분리한 값이 +를 포함하고 있는 경우	
				String[] tmp = arr[i].split("\\+"); // +를 기준으로 다시 분리해서 tmp에 저장
				
				// 분리한 값의 합을 sum에 저장
				for(int j = 0; j < tmp.length; j++) {
					sum += Integer.parseInt(tmp[j]);
				}
				
				if(i == 0) result += sum; // 첫 번째 값부터 빼면 안되기 때문에 결과에 더하고
				else result -= sum; // 나머지 경우는 뺀다.
			}
			else { // +를 포함하지 않는 경우 즉, 숫자 하나인 경우
				if(i == 0) result += Integer.parseInt(arr[i]);
				else result -= Integer.parseInt(arr[i]);
			}
		}
		
		System.out.print(result);
	}
}