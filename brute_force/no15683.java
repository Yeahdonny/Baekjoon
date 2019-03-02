package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no15683 {
	static int[][] space;
	static int N, M;
	static class Pair {
		int x, y, c;

		Pair(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}
	static int min = 999999999;
	public static void check(int[][] cctvs) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cctvs[i][j] == 0 && space[i][j] == 0) 
					cnt++;		
			}
		}
		min = Math.min(cnt, min);
	}

	public static void direction(int[][] cctvs, int startx, int starty, int dir, boolean flag) {
		if(dir>3)
			dir -= 4;
		switch (dir) {
		case 0:// 위로
			for (int i = startx; i >= 0; i--) {
				if (space[i][starty] == 6)
					return;
				if(flag)cctvs[i][starty]++;
				else cctvs[i][starty]--;
			}
			break;
		case 1:// 오른쪽
			for (int j = starty; j < space[0].length; j++) {
				if (space[startx][j] == 6)
					return;
				if(flag)cctvs[startx][j]++;
				else cctvs[startx][j]--;
			}
			
			break;
		case 2:// 아래
			for (int i = startx; i < space.length; i++) {
				if (space[i][starty] == 6)
					return;
				if(flag)cctvs[i][starty]++;
				else cctvs[i][starty]--;
			}
			break;
		case 3:// 왼쪽
			for (int j = starty; j >= 0; j--) {
				if (space[startx][j] == 6)
					return;
				if(flag)cctvs[startx][j]++;
				else cctvs[startx][j]--;
			}
			break;
		}
		
	}

	static LinkedList<Pair> cctv;

	public static void dfs(int[][] cctvs, int cnt) {
		if(cnt==cctv.size()) {
			check(cctvs);
			return;
		}
		int x = cctv.get(cnt).x; 
		int y = cctv.get(cnt).y; 
		int c = cctv.get(cnt).c; //cctv 번호
		
		if (c == 1) { //1번 cctv
			for (int i = 0; i < 4; i++) {
				direction(cctvs, x, y, i, true);
				dfs(cctvs, cnt + 1);
				direction(cctvs, x, y, i, false);
			}
		}
		else if(c==2) { //2번 cctv
			for(int i = 0 ; i < 2 ; i++) {
				direction(cctvs, x, y, i, true);
				direction(cctvs, x, y, i+2, true);
				dfs(cctvs, cnt+1);
				direction(cctvs, x, y, i, false);
				direction(cctvs, x, y, i+2, false);
			}
		}else if(c==3) {//3번 cctv
			for(int i = 0 ; i < 4 ; i++) {
				direction(cctvs, x, y, i, true);
				direction(cctvs, x, y, i+1, true);
				dfs(cctvs, cnt+1);
				direction(cctvs, x, y, i, false);
				direction(cctvs, x, y, i+1, false);
			}
		}else if(c==4) {//4번 cctv
			for(int i = 0 ; i < 4 ; i++) {
				direction(cctvs, x, y, i, true);
				direction(cctvs, x, y, i+1, true);
				direction(cctvs, x, y, i+2, true);
				dfs(cctvs, cnt+1);
				direction(cctvs, x, y, i, false);
				direction(cctvs, x, y, i+1, false);
				direction(cctvs, x, y, i+2, false);
			}
		}else if(c==5) {//5번 cctv
			for(int i = 0 ; i < 4  ; i ++)direction(cctvs, x, y, i, true);
			dfs(cctvs, cnt+1);
			for(int i = 0 ; i < 4  ; i ++)direction(cctvs, x, y, i, false);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		space = new int[N][M]; //사무실
		cctv = new LinkedList<>(); //cctv 위치와 번호 리스트
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if (space[i][j] != 0 && space[i][j] != 6)
					cctv.add(new Pair(i, j, space[i][j]));
			}
		}
		int[][] cctvs = new int[N][M];
		dfs(cctvs, 0);
		System.out.println(min);
	}
}
