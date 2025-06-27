package implementation;

import java.io.*;
import java.util.*;

public class IMPL14891 {
	static List<Gear> gears;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		gears = new ArrayList<>();
		gears.add(new Gear(null));
		for(int i = 1; i <= 4; i++) {
			int[] status = new int[8];
			String line = br.readLine();
			
			for(int j = 0; j < 8; j++) {
				status[j] = Character.getNumericValue(line.charAt(j));
			}
			
			gears.add(new Gear(status));
		}
		
		int k = Integer.parseInt(br.readLine());
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 회전시킬 모든 톱니바퀴의 번호와 방향을 저장
			List<Change> list = new ArrayList<>();
			
			// 처음 회전시킬 톱니바퀴의 번호와 방향
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			list.add(new Change(num, dir));
			
			// 처음 톱니바퀴의 왼쪽 톱니바퀴를 확인
			int currDir = dir;
			for(int j = num-1; j >= 1; j--) {
				if(gears.get(j+1).status[6] != gears.get(j).status[2]) {
					list.add(new Change(j, currDir*-1));
					currDir *= -1;
				}
				else break;
			}
			
			// 처음 톱니바퀴의 오른쪽 톱니바퀴를 확인
			currDir = dir;
			for(int j = num+1; j <= 4; j++) {
				if(gears.get(j-1).status[2] != gears.get(j).status[6]) {
					list.add(new Change(j, currDir*-1));
					currDir *= -1;
				}
				else break;
			}
			
			// 모든 톱니바퀴 회전시키기
			for(Change change : list) {
				turn(gears.get(change.num), change.dir);
			}
		}
		
		int score = 0;
		for(int i = 1; i <= 4; i++) {
			Gear gear = gears.get(i);
			if(i == 1) {
				if(gear.status[0] == 0) score += 0;
				else score += 1;
			}
			else if(i == 2) {
				if(gear.status[0] == 0) score += 0;
				else score += 2;
			}
			else if(i == 3) {
				if(gear.status[0] == 0) score += 0;
				else score += 4;
			}
			else {
				if(gear.status[0] == 0) score += 0;
				else score += 8;
			}
		}
		
		System.out.println(score);
	}
	
	private static void turn(Gear gear, int dir) {
		int[] newStatus = new int[8];
		
		// 톱니바퀴를 시계 방향으로 회전하는 경우
		if(dir == 1) {
			for(int i = 0; i < 8; i++) {
				newStatus[i] = gear.status[(i+7)%8];
			}
		}
		
		// 톱니바퀴를 반시계 방향으로 회전하는 경우
		else if(dir == -1) {
			for(int i = 0; i < 8; i++) {
				newStatus[i] = gear.status[(i+1)%8];
			}
		}
		
		// 톱니바퀴의 상태를 회전한 결과로 변경
		for(int i = 0; i < 8; i++) {
			gear.status[i] = newStatus[i];
		}
	}
	
	private static class Gear {
		private int[] status;
		
		Gear(int[] status) {
			this.status = status;
		}
	}
	
	private static class Change {
		private int num;
		private int dir;
		
		Change(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
}