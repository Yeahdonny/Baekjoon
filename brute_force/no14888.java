package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no14888 {
	public static int cal(int func, int cnt, int sum) {
		switch (func) {
		case 0:
			sum += number[cnt + 1];
			break;
		case 1:
			sum -= number[cnt + 1];
			break;
		case 2:
			sum *= number[cnt + 1];
			break;
		case 3:
			sum /= number[cnt + 1];
			break;
		}
		return sum;
	}

	static int N;
	static int[] func; //연산자
	static int[] number; //숫자
	static int MAX = -999999999;
	static int MIN = 999999999;

	public static void function(int cnt, int sum) {
		if (cnt == N - 1) {	 //모든 계산이 끝난 후
			MAX = Math.max(MAX, sum); //최댓값
			MIN = Math.min(MIN, sum); //최소값
			return;
		}	
		for ( int i = 0 ; i<4 ; i++) {
			if (func[i] != 0) {
				--func[i]; //해당 연산자 사용
				int result = cal(i, cnt, sum);//계산
				function(cnt + 1, result);
				++func[i];
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		number = new int[N]; 
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine(), " ");
		func = new int[4];
		for (int i = 0; i < 4; i++) {
			func[i] = Integer.parseInt(st.nextToken());
		}
		
		function(0, number[0]);
		System.out.println(MAX);
		System.out.println(MIN);

	}

}
