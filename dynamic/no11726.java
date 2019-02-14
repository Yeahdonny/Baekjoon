package dynamic;

import java.util.Scanner;

public class no11726 {
	/*public static int function(int a) {
		if(a == 1) return 1;
		else if (a == 2) return 2;
		return function(a-2) + function(a-1);
	}*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int tile[] = new int [1001];
		tile[1] = 1;
		tile[2] = 2;
		for(int i = 3; i < N+1 ; i++) {
			tile[i] = (tile[i-2]+tile[i-1])%10007;
		}
		System.out.println(tile[N]);
	}
}
