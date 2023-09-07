package algorithmStudy;

import java.io.*;

public class SORT1427 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine(); // 정렬하려고 하는 수 입력받기
		
		// 입력받은 수의 각 자리수를 분리해서 배열에 저장
		String[] arr = new String[s.length()];
		for(int i = 0; i < s.length(); i++) {
			arr[i] = Character.toString(s.charAt(i));
		}
		
		// 배열을 정렬
		String temp = "";
		for(int i = s.length()-1; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				if(Integer.parseInt(arr[j]) < Integer.parseInt(arr[j+1])) {
					temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		
		for(String i : arr) {
			bw.write(i);
		}
		bw.flush();
	}
}