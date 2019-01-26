package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class no14620 {
	static int N;
	static int flower[][];
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static int minMoney = 9999999;
	static boolean check[][]; // 꽃잎 or 씨앗 여부
	
	static boolean checkOk(int i, int j) {
		if (check[i][j] || check[i][j + 1] || check[i + 1][j] || check[i][j - 1] || check[i - 1][j])
			return true; // 이미  씨앗이 심어져 있거나 꽃잎이 있음
		return false; // 심어도 되는 곳
	}
	
	static void planting(int seed, int money, int count) {
		if (count == 3) { // 씨앗 3개 다 심었을 경우
			minMoney = Math.min(money, minMoney); 
			return;
		}
		for (int i = seed; i < N - 1; i++) {
			for (int j = 1; j < N - 1; j++) {
				if(checkOk(i, j)) continue; //심어도 되는 곳인지 check
				check[i][j] =true;
				int cost = flower[i][j]; //화단의 비용
				for (int k = 0; k < 4; k++) {
					int x = i + x_pos[k];
					int y = j + y_pos[k];
					check[x][y] = true;
					cost += flower[x][y];
				}
				
				planting(i, money + cost, count+1);
				
				check[i][j] = false;
				for (int k = 0; k < 4; k++) {
					int x = i + x_pos[k];
					int y = j + y_pos[k];
					check[x][y] = false;
				}
				
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		flower = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				flower[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		check = new boolean[N][N];
		planting(1, 0, 0); // 씨앗 심을 위치, 비용, 씨앗 심은 수
		
		System.out.println(minMoney);
	}

}
