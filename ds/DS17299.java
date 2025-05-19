package ds;

import java.io.*;
import java.util.*;

public class DS17299 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		Stack<Integer> stack = new Stack<>(); // Ai의 오른쪽 수들을 저장
		HashMap<Integer, Integer> map = new HashMap<>(); // Ai가 수열에서 등장한 횟수를 저장
		List<Integer> list = new ArrayList<>();
		StringBuilder result = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(!map.containsKey(arr[i])) {
				map.put(arr[i], 1);
			}
			else map.put(arr[i], map.get(arr[i])+1);
		}
		
		for(int i = n-1; i >= 0; i--) {
			// Ai의 오른쪽에 있는 수들 중에서 Ai보다 수열에서 등장한 횟수가 작거나 같은 수들은 오등큰수가 될 수 없음 
			while(!stack.isEmpty() && map.get(stack.peek()) <= map.get(arr[i])) {
				stack.pop();
			}
			// Ai의 오른쪽에 있는 수가 없다는 뜻이므로 리스트에 -1 추가
			if(stack.isEmpty()) {
				list.add(-1);
			}
			// 리스트에 오등큰수 추가
			else {
				list.add(stack.peek());
			}
			stack.push(arr[i]);
		}
		
		// 뒤에서부터 오등큰수를 계산했으므로 리스트를 뒤집은 것이 결과
		for(int i = list.size()-1; i >= 0; i--) {
			result.append(list.get(i)).append(" ");
		}
		
		System.out.println(result);
	}
}