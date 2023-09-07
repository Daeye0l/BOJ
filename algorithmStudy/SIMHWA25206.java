package algorithmStudy;

import java.io.*;
import java.util.*;

public class SIMHWA25206 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		double total_score = 0; // 학점 x 과목평점의 총합
		double total_credit = 0; // 학점의 총합

		for(int i = 0; i < 20; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			st.nextToken();
			double credit = Double.parseDouble(st.nextToken()); // 학점
			String grade = st.nextToken(); // 등급
			
			// 각 등급에 따라 학점 * 과목평점을 해서 total_score에 더하기
			if(grade.equals("A+")) total_score += 4.5 * credit;
			else if(grade.equals("A0")) total_score += 4.0 * credit;
			else if(grade.equals("B+")) total_score += 3.5 * credit;
			else if(grade.equals("B0")) total_score += 3.0 * credit;
			else if(grade.equals("C+")) total_score += 2.5 * credit;
			else if(grade.equals("C0")) total_score += 2.0 * credit;
			else if(grade.equals("D+")) total_score += 1.5 * credit;
			else if(grade.equals("D0")) total_score += 1.0 * credit;
			else if(grade.equals("F") || grade.equals("P")) total_score += 0.0 * credit;
			
			// P 등급인 경우 계산에서 제외
			if(grade.equals("P")) {
				continue;
			}
			else {
				total_credit += credit;
			}
			
		}
		System.out.printf("%.6f", total_score / total_credit);
	}
}