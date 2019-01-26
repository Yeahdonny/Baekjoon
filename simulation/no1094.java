package simulation;

import java.util.Scanner;

public class no1094 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int stick = 64;
		int count = 0;
		int sum = 0;

		if (stick == x) {
			System.out.println("1");
			return;
		}
		while (sum != x) {
			stick = stick / 2;
			if (stick + sum <= x) {
				sum += stick;
				count++;
			}
		}
		System.out.println(count);
	}

}
