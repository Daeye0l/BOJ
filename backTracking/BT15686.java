package backTracking;

import java.io.*;
import java.util.*;

public class BT15686 {
	static int n, m, min;
	static int[][] map;
	static List<Location> houseLocation, chickenLocation, selectedChicken;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		min = Integer.MAX_VALUE;
		map = new int[n][n];
		houseLocation = new ArrayList<Location>();
		chickenLocation = new ArrayList<Location>();
		selectedChicken = new ArrayList<Location>();
		
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 1) houseLocation.add(new Location(i, j));
				else if(map[i][j] == 2) chickenLocation.add(new Location(i, j));
			}
		}
		
		select(0, 0);
		System.out.println(min);
	}
	
	private static void select(int idx, int cnt) {
		// 치킨집 m개를 선택한 경우
		if(cnt == m) {
			int cityDistance = 0; // 도시의 치킨 거리
			for(int i = 0; i < houseLocation.size(); i++) {
				// 각 집에 대해 치킨 거리를 구해서
				int houseDistance = Integer.MAX_VALUE; // 집의 치킨 거리
				Location house = houseLocation.get(i);
				
				for(int j = 0; j < selectedChicken.size(); j++) {
					Location chicken = selectedChicken.get(j);
					houseDistance = Math.min(houseDistance, Math.abs(house.x-chicken.x) + Math.abs(house.y-chicken.y));
				}
				// 도시의 치킨 거리에 누적
				cityDistance += houseDistance;
			}
			
			// 최소 값을 갱신 후 종료
			min = Math.min(min, cityDistance);
			return;
		}
		
		for(int i = idx; i < chickenLocation.size(); i++) {
			selectedChicken.add(chickenLocation.get(i));
			select(i+1, cnt+1);
			selectedChicken.remove(selectedChicken.size()-1);
		}
	}
	
	static class Location {
		private int x;
		private int y;
		
		Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}