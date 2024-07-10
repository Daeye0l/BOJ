package ds;

import java.io.*;
import java.util.*;

public class DS17298 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		Stack<Integer> stack = new Stack<>();
		List<Integer> result = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = n-1; i >= 0; i--) {
			/* numbers[i] >= stack.peek()에서 스택에 들어있는 수들은 numbers[i] 오른쪽에 있는 수들이기 때문에
			numbers[i]보다 작다는 것은 numbers[i]보다 앞쪽에 있는 수들의 오큰수가 될 수 없으므로 pop해도 무방 */
			while(!stack.isEmpty() && numbers[i] >= stack.peek()) {
				stack.pop();
			}
			if(stack.isEmpty()) { // 오큰수가 없는 경우
				result.add(-1);
			}
			else { // 오른쪽에 있는 수들 중 작거나 같은 수를 모두 pop했으므로 스택에 맨 위에 있는 수가 오큰수 
				result.add(stack.peek());
			}
			stack.push(numbers[i]);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = result.size()-1; i >= 0; i--) {
			sb.append(result.get(i)).append(" ");
		}
		System.out.println(sb);
	}
}