package dynamic;

import java.util.Scanner;

public class no1003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int fibo;
		int zero[] = new int[41];
		int one[] = new int[41];
		zero[0]=1;
		one[1]=1;
		for (int j = 2; j < 41; j++) {
			zero[j] =  one[j-1];
			one[j] =  zero[j-1] + one[j-1];
			System.out.println(zero[j]+" "+one[j] );
		}
		for (int i = 0; i < T; i++) {
			fibo = scan.nextInt();		
			System.out.println(zero[fibo] + " " + one[fibo]);
		}
	}

}
