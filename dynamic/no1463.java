package dynamic;

import java.util.Scanner;

public class no1463 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int x = scan.nextInt();
		int oper[] = new int [x+1]; //입력한 숫자 까지 연산하기 위해 +1
		oper [1] = 0; //입력한 숫자가 1일 경우 계산 횟수는 0
		for(int i = 2 ; i<=x;i++) {	//2부터 입력한 숫자 까지 연산, 입력한 숫자에서 계산하는게 아니라 거꾸로 2부터 입력한 숫자 까지 연산
			oper [i] = oper[i-1] + 1; //3과 2로 나누어 떨어지지 않는 경우 1을 빼는 경우,  연산 행위 +1 
			if (i%2==0) {	//2로 나누어 떨어질 경우
				oper[i]=Math.min(oper[i], oper[i/2] +1); //1을 뺐을 때와 2로 나누었을때의 연산 횟수를 비교하여 더 적은 횟수를 대입
			}
			if(i%3==0) {	//3으로 나누어 떨어질 경우 (6의 배수 고려)
				oper[i] = Math.min(oper[i], oper[i/3]+1); //1을 뺐을 때 또는 2로 나누어었을 때와 3으로 나누었을때의 연산 횟수를 비교하여 더 적은 횟수를 대입
			}
		}
		System.out.println(oper[x]);
	}
}
