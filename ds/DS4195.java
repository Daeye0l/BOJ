package ds;

import java.io.*;
import java.util.*;

public class DS4195 {
	static Map<String, String> parent;
	static Map<String, Integer> size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		for(int t = 0; t < T; t++) {
			parent = new HashMap<>();
			size = new HashMap<>();
			
			int F = Integer.parseInt(br.readLine());
			
			for(int f = 0; f < F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if(!parent.containsKey(a)) {
					parent.put(a, a);
					size.put(a, 1);
				}
				if(!parent.containsKey(b)) {
					parent.put(b, b);
					size.put(b, 1);
				}
				
				result.append(union(a, b)).append('\n');
			}
		}
		
		System.out.println(result);
	}
	
	private static int union(String a, String b) {
		String aRoot = find(a);
		String bRoot = find(b);
		
		if(aRoot.equals(bRoot)) {
			return size.get(aRoot);
		}
		
		parent.put(bRoot, aRoot);
		size.put(aRoot, size.get(aRoot) + size.get(bRoot));
		size.remove(bRoot);
		
		return size.get(aRoot);
	}
	
	private static String find(String a) {
		if(a.equals(parent.get(a))) return a;
		parent.put(a, find(parent.get(a)));
		return parent.get(a);
	}
}