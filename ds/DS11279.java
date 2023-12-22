package ds;

import java.io.*;
import java.util.*;

public class DS11279 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine()); // 연산의 개수 입력받기
		ArrayList<Integer> heap = new ArrayList<>(); // 최대힙으로 사용할 ArrayList 생성
		heap.add(-1); // 인덱스를 1부터 사용하기 위해 더미값 추가
		
		for(int i = 0; i < N; i++) { // 연산 횟수만큼 반복
			int x = Integer.parseInt(br.readLine()); // x 입력 받기
			
			if(x == 0) { // x가 0일 때
				if(heap.size() - 1 < 1) System.out.println("0"); // 힙이 더미값만 남았다면 0 출력
				else { // 힙이 비어있지 않다면
					swap(heap, 1, heap.size() - 1); // 루트와 마지막 노드를 교환
					System.out.println(heap.remove(heap.size() -1)); // 최대값 출력
					downHeapify(heap);
				}
			}
			else {
				heap.add(x);
				upHeapify(heap);
			}
		}
	}
	
	private static void downHeapify(ArrayList<Integer> heap) {
		if(heap.size() - 1 < 1) return; // 더미값만 남아있다면 종료
		else {
			int pos = 1; // 루트의 인덱스
			
			while(pos < heap.size()) {
				int left = pos * 2; // 왼쪽 자식의 인덱스
				int right = pos * 2 + 1; // 오른쪽 자식의 인덱스
				int max = 0; // 값이 큰 자식의 인덱스
				
				// 두 자식이 모두 있는 경우
				if(right < heap.size()) {
					if(heap.get(left) > heap.get(right)) max = left;
					else max = right;
				}
				// 왼쪽 자식만 있는 경우
				else if(left < heap.size()) max = left;
				
				// 부모노드의 값이 자식 노드의 값보다 작다면 값 교환
				if(heap.get(pos) < heap.get(max)) {
					swap(heap, pos, max);
					pos = max;
				}
				else { // 아니라면 종료
					return;
				}
			}
		}
	}
	
	private static void upHeapify(ArrayList<Integer> heap) {
		int pos = heap.size()-1; // 힙의 마지막 노드의 인덱스
		
		// 부모노드의 값이 자식노드의 값보다 작다면
		while(pos > 1 && heap.get(pos / 2) < heap.get(pos)) {
			// 부모노드와 자식 노드의 값을 교환
			swap(heap, pos / 2, pos);
			pos = pos / 2;
		}
	}
	
	private static void swap(ArrayList<Integer> heap, int i, int j) {
		int temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
}