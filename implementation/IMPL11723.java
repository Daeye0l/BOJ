package implementation;

import java.io.*;
import java.util.*;

public class IMPL11723 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String command = st.nextToken();
			if(st.hasMoreTokens()) {
				int x = Integer.parseInt(st.nextToken());
				if(command.equals("add")) {
					set.add(x);
				}
				else if(command.equals("remove")) {
					set.remove(x);
				}
				else if(command.equals("check")) {
					if(set.contains(x)) sb.append(1);
					else sb.append(0);
					sb.append("\n");
				}
				else if(command.equals("toggle")) {
					if(set.contains(x)) set.remove(x);
					else set.add(x);
				}
			}
			else {
				if(command.equals("all")) {
					for(int j = 1; j <= 20; j++) set.add(j);
				}
				else {
					set = new HashSet<>();
				}	
			}
		}
		System.out.println(sb);
	}
}