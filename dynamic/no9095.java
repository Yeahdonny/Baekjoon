package dynamic;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class no9095 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 숫자 n이 주어졌을 때, n을 1, 2, 3의 합으로 나타내는 방법의 수 구하기
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt(); // 테스트 케이스 개수
		int N[] = new int [T]; // 테스트할 정수 모음
		int max = 0;
		for(int i = 0 ; i < T ; i++) {
			N[i] = scan.nextInt();
			if(max < N[i]) max = N[i];
		}
		int method[] = new int[max+1];
		method[0] = 0;
		method[1] = 1; //정수가 1 일 때 테스트 수 = 1 (1)
		method[2] = 2; //정수가 2 일 때 테스트 수 = 2 (1+1), (2)
		method[3] = 4; //정수가 3 일 때 테스트 수 = 4 (1+1+1), (2+1), (1+2), (3)
		
		
		if(max > 3) {
			for(int i = 4 ; i <= max ;i++) {
				//System.out.println(i+" "+method[i-3]+" "+method[i-2]+" "+method[i-1]);
				method[i] = method[i-3] + method[i-2] + method[i-1];
				//System.out.println(i+" "+method[i]);
			}
		}
		for(int i = 0 ; i < T; i++) {
			System.out.println(method[N[i]]);
		}
	}

}
