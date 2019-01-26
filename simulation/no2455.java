package simulation;

import java.util.Scanner;

public class no2455 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner (System.in);
		int max = 0;
		int people = 0;
		for(int i = 0 ; i < 4; i ++) {
			int minus = scan.nextInt(); //내렸을 때
			int plus = scan.nextInt(); //탔을 때
			people = people - minus + plus;
			max = Math.max(max, people);
		}
		System.out.println(max);
	}

}
