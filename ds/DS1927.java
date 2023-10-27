package ds;

import java.io.*;
import java.util.*;

public class DS1927 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine());
			
			if(heap.size() == 0 && value == 0) {
				System.out.println(0);
			}
			else {
				if(value == 0) {
					System.out.println(heap.remove());
				}
				else heap.add(value);
			}
		}
	}
}