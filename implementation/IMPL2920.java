package implementation;

import java.io.*;
import java.util.*;

public class IMPL2920 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		String state = "";
		
		int num = 0;
		if(first == 1) {
			num = 2;
			state = "ascending";
			for(int i = 0; i < 7; i++) {
				if(num == Integer.parseInt(st.nextToken())) {
					num++;
				}
				else {
					state = "mixed";
					break;
				}
			}
		}
		else if(first == 8) {
			num = 7;
			state = "descending";
			for(int i = 0; i < 7; i++) {
				if(num == Integer.parseInt(st.nextToken())) {
					num--;
				}
				else {
					state = "mixed";
					break;
				}
			}
		}
		else {
			state = "mixed";
		}
		
		System.out.println(state);
	}
}