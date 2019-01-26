package dfs_bfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class no1012 {
	static boolean check[][];
	static int x[] = {0, 1, 0, -1};
	static int y[] = {1, 0, -1, 0};
	static int field[][];
	
	public static void dfs(int N, int M) {
		check[N][M] = true;
		field[N][M] = -1;
		//System.out.println(field[N][M]);

		for(int i = 0 ; i < 4 ;i++) {
			int goN = N + x[i];
			int goM = M + y[i];
			if(goN < 0 || goM < 0 || goN >= field.length ||goM >= field[0].length)
				continue;
			if(field[goN][goM] == 1 && check[goN][goM] == false) {
				dfs(goN, goM);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt(); //테스트 케이스의 개수
		int answer[] = new int [T];
		for(int i = 0 ; i < T ; i ++) {
			int M = scan.nextInt(); //배추밭의 가로 길이
			int N = scan.nextInt(); //배추밭의 세로 길이
			int K = scan.nextInt(); //배추가 심어져 있는 위치의 개수
			field = new int [N][M];
			
		for(int j = 0 ; j < K ; j ++) {
			int X = scan.nextInt(); // M
			int Y = scan.nextInt(); // N
			field[Y][X] = 1;	//field[N][M]
		}
		check = new boolean[N][M];
		//System.out.println("N: "+N +" M: "+ M);
		int count = 0;
		for(int a = 0 ; a < N ; a++) {
			for(int b = 0 ; b < M ; b++) {
				if(field[a][b] == 1) { //field[N][M]
					dfs(a, b);
					count++;
				}
			}
		}
		answer[i] = count;
		}
		
		
		for(int i = 0 ; i < T ; i++) {
			System.out.println(answer[i]);
		}
		
	}
 
}
