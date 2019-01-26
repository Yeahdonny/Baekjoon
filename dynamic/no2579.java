package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class no2579 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int stairs = scan.nextInt();
		int []score = new int [stairs+1];	// 각 계단 별 점수
		int []dp = new int[stairs+1];	// 각 계단에 도달했을 때 얻은 총 점수의 최댓값
		score[0] = 0;
		for(int i = 1 ; i <= stairs ; i++) {
			score[i] = scan.nextInt(); 	//계단 마다 점수 입력
		}
		dp[1] = score[1];
		dp[2] = Math.max(score[2], dp[1]+score[2]);  
		/**
		 * 
		 * 마지막 계단에 도달하기 위한 방법은 두 가지가 있음
		 * 마지막 계단이 N이라고 했을 때,
		 * 1) N-3 -> N-1 -> N
		 * 2) N-2 -> N
		 * 연달에 세개의 계단을 밟을 수 없기 때문에 위와 같은 경우가 나옴
		 * 
		 * */
		for(int i = 3; i <=stairs ; i++) {
			/**
			 * 
			 * dp[i-3] : i-3번째 계단까지 올라 갔을 때의 최고 점수
			 * dp[i-2] : i-2번째 계단까지 올라 갔을 때의 최고 점수 
			 * 
			 * */
			dp[i] = Math.max(dp[i-3]+score[i-1]+score[i], dp[i-2] + score[i]);
		}
		
		System.out.println(dp[stairs]);
		
	}
}
