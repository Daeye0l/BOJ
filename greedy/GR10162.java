package greedy;

import java.io.*;
import java.util.*;

public class GR10162 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine()); // 요리해야할 시간(초)
		int[] button = {300, 60, 10}; // 각 버튼의 시간
		HashMap<Integer, Integer> map = new HashMap<>();
		
		 // 각 버튼의 시간과 누른 횟수로 해시맵 채우기
		for(int i = 0; i < button.length; i++) {
			if(!map.containsKey(button[i])) map.put(button[i], 0);
		}
		
		// 각 버튼에 대하여 눌러야 하는 횟수 채우기
		int j = 0;
		while(j < button.length && t > 0) {
			int b = button[j];
			if(t / b != 0) {
				map.put(b, map.get(b) + (t / b));
				t %= b;
			}
			j++;
		}
		
		if(t != 0) System.out.print(-1); // 3개의 버튼으로 T초를 맞출 수 없는 경우
		else {
			for(int i = 0; i < button.length; i++) {
				System.out.print(map.get(button[i]) + " ");
			}
		}
	}
}