package bruteforcing;

import java.io.*;
import java.util.*;

public class BRUTE16637 {
	static int max;
	static List<Integer> numbers;
	static List<Character> operators;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		numbers = new ArrayList<>();
		operators = new ArrayList<>();
		max = -Integer.MAX_VALUE;
		
		String s = br.readLine();
		for(int i = 0; i < n; i++) {
			char c = s.charAt(i);
			// 연산자와 숫자를 구분해서 리스트에 추가
			if(c >= 42 && c <= 45) operators.add(c);
			else numbers.add(Character.getNumericValue(c));
		}
		
		dfs(numbers.get(0), 0);
		System.out.println(max);
	}
	
	private static void dfs(int result, int idx) {
		// 연산자를 모두 사용했다면 최댓값 갱신 후 종료
		if(idx == operators.size()) {
			max = Math.max(max, result);
			return;
		}
		
		// 괄호를 사용하지 않은 경우는 연산자 인덱스를 늘려가며 재귀를 통해 최댓값 갱신
		// 이 경우도 생각해보면 계산결과와 그 다음 수를 괄호로 묶어서 진행하는 것과 같음
		dfs(calc(result, numbers.get(idx+1), operators.get(idx)), idx+1);
		
		// 괄호를 사용하는 경우
		if(idx+1 < operators.size()) {
			// 현재까지 계산한 값 기준 오른쪽 2개 수를 먼저 계산(괄호 적용)
			int calcRight = calc(numbers.get(idx+1), numbers.get(idx+2), operators.get(idx+1));
			// 현재까지의 값과 괄호를 적용한 값으로 다시 계산하고 다다음 연산자 인덱스를 통해 재귀
			dfs(calc(result, calcRight, operators.get(idx)), idx+2);
		}
	}
	
	private static int calc(int a, int b, char op) {
		if(op == '+') return a + b;
		else if(op == '-') return a - b;
		else return a * b;
	}
}