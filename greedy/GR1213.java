package greedy;

import java.io.*;

public class GR1213 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String name = br.readLine();
		int[] cnt = new int[26];
		char[] result = new char[name.length()];
		
		// 각 알파벳의 개수 저장
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			cnt[c-'A']++;
		}
		
		// 개수가 홀수개인 알파벳 세기
		int oddCnt = 0;
		int oddIdx = -1;
		for(int i = 0; i < 26; i++) {
			if(cnt[i] % 2 != 0) {
				oddCnt++;
				oddIdx = i;
			}
		}
		
		// 홀수개인 알파벳이 없거나 1개만 있어야 팰린드롬을 만들 수 있다
		if(oddCnt > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		
		int i = 0; 
		int j = name.length()-1;
		// 사전순으로 앞서는 것이 정답이므로 2개 이상이라면 계속 사용 
		for(int k = 0; k < 26; k++) {
			while(cnt[k] >= 2) {
				result[i] = (char)(k+'A');
				result[j] = (char)(k+'A');
				cnt[k] -= 2;
				i++; j--;
			}
		}
		
		// 홀수개인 알파벳이 하나 있었다면 남은 그 알파벳을 사용
		if(oddCnt == 1) {
			result[i] = (char)(oddIdx +'A');
		}
		
		StringBuilder sb = new StringBuilder();
		for(char c : result) {
			sb.append(c);
		}
		
		System.out.println(sb);
	}
}