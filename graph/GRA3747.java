package graph;

import java.io.*;
import java.util.*;

public class GRA3747 {
	static List<List<Integer>> graph;
	static int idx, sccIdx;
	static int[] id, low, sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	   
	    StringBuilder result = new StringBuilder();
	    String line = "";
	    StringTokenizer st = null;
	    while (true) {
	        while (st == null || !st.hasMoreTokens()) {
	            line = br.readLine();
	            if (line == null) {
	                System.out.print(result);
	                return;
	            }
	            st = new StringTokenizer(line);
	        }
	        
	        if (!st.hasMoreTokens()) continue;
	        int N = Integer.parseInt(st.nextToken());
	        while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
	        int M = Integer.parseInt(st.nextToken());

	        init(N);

	        for (int m = 0; m < M; m++) {
	            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
	            String i = st.nextToken();
	            
	            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
	            String j = st.nextToken();
	            
	            addEdge(i, j);
	        }

	        for (int n = 1; n <= 2*N; n++) {
	            if (id[n] == 0) dfs(n);
	        }

	        boolean isValid = true;
	        for (int n = 1; n <= N; n++) {
	            int pos = 2*n-1;
	            int neg = 2*n;
	            if (sccId[pos] == sccId[neg]) {
	                isValid = false;
	                break;
	            }
	        }

	        if (isValid) result.append(1);
	        else result.append(0);
	        result.append('\n');
	    }
	}
	
	private static void dfs(int curr) {
		id[curr] = low[curr] = ++idx;
		stack.push(curr);
		onStack[curr] = true;
		
		for(int next : graph.get(curr)) {
			if(id[next] == 0) {
				dfs(next);
				low[curr] = Math.min(low[curr], low[next]);
			}
			else if(onStack[next]) {
				low[curr] = Math.min(low[curr], id[next]);
			}
		}
		
		if(id[curr] == low[curr]) {
			sccIdx++;
			while(true) {
				int v = stack.pop();
				onStack[v] = false;
				sccId[v] = sccIdx;
				if(v == curr) break;
			}
		}
	}
	
	private static void addEdge(String i, String j) {
		char signI = i.charAt(0);
		char signJ = j.charAt(0);
		int numI = Integer.parseInt(i.substring(1));
		int numJ = Integer.parseInt(j.substring(1));
		
		// i와 j 둘 중 한 명은 당선 -> i u j
		if(signI == '+' && signJ == '+') {
			graph.get(changeNum(-numI)).add(changeNum(numJ));
			graph.get(changeNum(-numJ)).add(changeNum(numI));
		}
		// i와 j 둘 중 한 명은 떨어짐 -> !i u !j
		else if(signI == '-' && signJ == '-') {
			graph.get(changeNum(numI)).add(changeNum(-numJ));
			graph.get(changeNum(numJ)).add(changeNum(-numI));
		}
		// i가 붙거나 j가 떨어지거나, 둘 다 만족 -> !(!i n j) -> i u !j
		else if(signI == '+' && signJ == '-') {
			graph.get(changeNum(-numI)).add(changeNum(-numJ));
			graph.get(changeNum(numJ)).add(changeNum(numI));
		}
		// j가 붙거나 i가 떨어지거나, 둘 다 만족 -> !(!j n i) -> j u !i
		else {
			graph.get(changeNum(-numJ)).add(changeNum(-numI));
			graph.get(changeNum(numI)).add(changeNum(numJ));
		}
	}
	
	private static int changeNum(int num) {
		return num > 0 ? 2*num-1 : 2*(-num);
	}
	
	private static void init(int N) {
		graph = new ArrayList<>();
		idx = sccIdx = 0;
		id = new int[2*N+1];
		low = new int[2*N+1];
		sccId = new int[2*N+1];
		stack = new Stack<>();
		onStack = new boolean[2*N+1];
		
		for(int n = 0; n <= 2*N; n++) {
			graph.add(new ArrayList<>());
		}
	}
}