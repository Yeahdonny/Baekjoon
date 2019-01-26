package dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class no2667 {
	static int []x = {0,  1,  0,  -1};
	static int []y = {1,  0,  -1,  0};
	static int count =0;
	static int [][]maps;
	static int num[]=new int [(25*25)/2]; //여기서 단순하게 N의 값을 주면 된다고 생각했었음.. 단지 내 집의 수가 N개 까지 있으란 법은 없어,,!
	public static void dfs(int mx , int my) {
		maps[mx][my] = -1;
		num[count]++;
		for(int i = 0 ; i < 4 ; i ++) {
			int msx = mx + x[i];
			int msy = my + y[i];
			if(msx < 0 || msy < 0 || msx >= maps.length || msy >= maps.length)
				continue;
			if(maps[msx][msy] == 1) 				
				dfs(msx, msy);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//첫번째 줄에는 총 단지 수 출력
		//이후 각 단지 내 집의 수 오름차순으로 정렬
		//집 있는 곳은 1 없는곳은 0
		//대각선 상에 있는 집 연결 x
		
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // 지도의 크기 
		maps= new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			String str = scan.next();
			for(int j = 0 ; j < str.length() ; j++) {	
				maps[i][j] = str.charAt(j)-'0';
			}
		}
	
		for(int  i = 0 ; i< N ;i++) {
			for(int j = 0 ;j < N ; j++) {
				if(maps[i][j] == 1) {
					dfs(i, j);
					count++;
				}
			}
		}
		System.out.println(count);
		Arrays.sort(num); //Arrays.sort는 오름차순만 지원
		
		for(int i = num.length-count ; i < num.length; i ++) {
			System.out.println(num[i]);
		}
	}

}
