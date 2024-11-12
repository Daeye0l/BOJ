package greedy;

import java.io.*;
import java.util.*;

public class GR1092 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		List<Integer> crain = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			crain.add(Integer.parseInt(st.nextToken()));
		}
		// 크레인의 무게 제한을 내림차순으로 정렬
		Collections.sort(crain, Collections.reverseOrder());
		
		int m = Integer.parseInt(br.readLine());
		List<Integer> box = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			box.add(Integer.parseInt(st.nextToken()));
		}
		// 상자의 무게 또한 내림차순으로 정렬
		Collections.sort(box, Collections.reverseOrder());
		
		// 가장 무거운 상자를 무게 제한이 가장 큰 크레인으로도 옮길 수 없는 경우
		if(crain.get(0) < box.get(0)) {
			System.out.println(-1);
			return;
		}
		
		int time = 0;
		// 모든 박스를 운반할 때까지 반복
		while(!box.isEmpty()) {
			for(int i = 0; i < crain.size(); i++) {
				int crainLimit = crain.get(i);
				for(int j = 0; j < box.size(); j++) {
					// 박스를 크레인으로 옮길 수 있는 경우 옮기기
					if(box.get(j) <= crainLimit) {
						box.remove(j);
						break;
					}
				}
			}
			time++;
		}
	
		System.out.println(time);
	}
}