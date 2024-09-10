package backTracking;

import java.io.*;
import java.util.*;

public class BT1759 {
	static int l, c;
	static char[] chars, password;
	static boolean[] used;
	static StringBuilder result;
	static List<Character> vowels;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		chars = new char[c]; // 문자를 저장
		password = new char[l]; // 출력할 암호를 저장
		used = new boolean[c]; // 문자의 사용 여부를 저장
		result = new StringBuilder();
		vowels = new ArrayList<Character>();
		vowels.add('a'); vowels.add('e'); vowels.add('i');
		vowels.add('o'); vowels.add('u');
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < c; i++) {
			chars[i] = st.nextToken().charAt(0);
		}
		// 사전순 출력을 위해 미리 정렬
		Arrays.sort(chars);
		
		makePassword(0, 0);
		System.out.println(result);
	}
	
	private static void makePassword(int length, int idx) {
		// l길이의 암호가 완성되면 sb에 추가 후 종료
		if(length == l) {
			int vowelCnt = 0;
			int consonantCnt = 0;
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < l; i++) {
				if(vowels.contains(password[i])) vowelCnt +=1;
				else consonantCnt += 1;
				sb.append(password[i]);
			}
			
			// 모음이 1개 이상이고 자음이 2개 이상인 경우에만 결과에 추가
			if(vowelCnt >= 1 && consonantCnt >= 2) {
				result.append(sb).append("\n");
			}
			
			return;
		}
		
		// 중복을 허용하지 않고 선택
		for(int i = idx; i < c; i++) {
			if(!used[i]) {
				used[i] = true;
				password[length] = chars[i];
				makePassword(length+1, i+1);
				used[i] = false;
			}
		}
	}
}