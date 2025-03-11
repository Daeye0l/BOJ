package graph;

import java.io.*;
import java.util.*;

public class GRA1865 {
	static int INF = 30000000;
	static List<Edge> edges;
	static int[] distance;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tc = Integer.parseInt(br.readLine());
		for(int i = 0; i < tc; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges = new ArrayList<>();
			distance = new int[n+1];
			
			// 도로의 정보 s지점에서 e지점까지 t시간이 걸림
			for(int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				// 간선 정보 저장
				edges.add(new Edge(s, e, t));
				edges.add(new Edge(e, s, t));
			}
			
			// 웜홀의 정보 s에서 e지점으로 t시간이 줄어듬
			for(int j = 0; j < w; j++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				// 간선 정보 저장
				edges.add(new Edge(s, e, -t));
			}
			
			// 모든 정점이 연결되어 있다는 보장이 없으므로 가상의 정점을 모든 정점에 연결
			for(int j = 1; j <= n; j++) {
				edges.add(new Edge(0, j, 0));
			}
			
			if(bellmanFord(n, 0)) bw.write("YES\n");
			else bw.write("NO\n");
		}
		
		bw.flush();
		bw.close();
	}
	
	private static boolean bellmanFord(int  n, int start) {
		for(int j = 1; j <= n; j++) distance[j] = INF;
		distance[start] = 0;
		
		// 모든 간선에 대해 정점만큼 반복하여 갱신
		for(int i = 0; i < n; i++) {
			for(Edge edge : edges) {
				if(distance[edge.s] != INF && distance[edge.s] + edge.t < distance[edge.e]) {
					distance[edge.e] = distance[edge.s] + edge.t;
				}
			}
		}
		
		// 모든 경우를 확인하고도 거리를 업데이트 하는 경우가 생긴다면 음수 사이클이 존재
		for(Edge edge : edges) {
			if(distance[edge.s] != INF && distance[edge.s] + edge.t < distance[edge.e]) {
				return true;
			}
		}
		
		return false;
	}
	
	private static class Edge {
		private int s;
		private int e;
		private int t;
		
		Edge(int s, int e, int t) {
			this.s = s;
			this.e = e;
			this.t = t;
		}
	}
 }