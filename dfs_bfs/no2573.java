package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no2573 {
	static int N, M;
	static int sea[][];
	static int x_pos[] = { 1,  0,  -1,  0 };
	static int y_pos[] = { 0,  1,  0,  -1};
	static boolean check[][];
	static int melt = 0;

	static void checkDFS(int x, int y) {
		check[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int goX = x + x_pos[i];
			int goY = y + y_pos[i];
			if (goX < 0 || goY < 0 || goX >= N || goY >= M)
				continue;
			if (sea[goX][goY] != 0 && check[goX][goY] == false) // if 주변이 연결되어 있다면
				checkDFS(goX, goY);
		}

	}

	static void meltCheck(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int goX = x + x_pos[i];
			int goY = y + y_pos[i];
			if (goX < 0 || goY < 0 || goX >= N || goY >= M)
				continue;
			if (sea[goX][goY] == 0) {// if 주변이 바다면
				melt++;
			}
		}
	}

	static int goCheck() {
		int count = 0;
		check = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sea[i][j] != 0 && check[i][j] == false) {
					checkDFS(i, j);
					count++;
				}
			}
		}
		return count;
	}

	static void goMelt() {
		int meltSea[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (sea[i][j] != 0) {
					melt = 0;
					meltCheck(i, j);
					meltSea[i][j] = melt;
				}
			}
		}
		for(int i = 0; i < N ; i++) {
			for (int j = 0; j < M; j++) {
				sea[i][j] -= meltSea[i][j];
				if(sea[i][j]<0) 
					sea[i][j]=0;
			}
		}
	}
	static void print() { //출력 테스트용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(sea[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		sea = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			while (st.hasMoreTokens()) {
				for (int j = 0; j < M; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}
		int year = 0;
		while (true) {
			int a = goCheck();
			if (a == 1) { //덩이가 한개
				goMelt();
				year++;
			} else if (a == 0) { //다 녹을 때 까지 분리 x
				System.out.println("0");
				return;
			} else { //덩이가 1개 이상
				System.out.println(year);
				return;
			}
		}

	
		
	}

}
