package brute_force;
import java.util.Scanner;

public class no1065 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int N= scan.nextInt(); // 숫자 입력 받음 
		    int count = 0;
		 	int check = 0;
			int j ;
		for(int i = 1 ; i <= N; i++) {
			String number = String.valueOf(i);
			if(number.length() < 3) count++;
			else {
				int []value = new int[number.length()];
				int a = i;
				for(j = 0 ; j < value.length ; j++) {
					value[j] = a % 10;
					a /= 10;
				}
				
				for(j = 0 ; j < value.length-1; j++) {
					
					if(j == 0) {
						check = value[j+1] - value[j];
						continue;
					}
					int sub = value[j+1] - value[j];
					if(check  !=  sub) {
						break;
					}
				}
				if(j == value.length-1) {
					count ++;
				}
			}
			
		}
		System.out.println(count);
	}

}
