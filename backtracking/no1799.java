package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no1799 {
	static int bishop = 0;
	static boolean check[][]; //비숍이 놓였다면 true, 놓이지 않았다면 false
	static int chess[][]; //체스 판

	//비숍을 놓을 수 있는지 확인
	public static boolean isPossible(int i, int j) {
		for (int x = 0; x < i; x++) {
			for (int y = 0; y < chess.length; y++) {
				if (i - x == Math.abs(j - y) && chess[x][y] == 1 && check[x][y]) { //대각선 방향으로 이전에 비숍이 놓아졌는지 확인
					return false; //이전에 비숍이 놓아져 있음
				}
			}
		}
		check[i][j] = true; //현재 위치에 비숍을 놨음
		return true;//현재 위치에 비숍을 놓을 수 있음
	}
	//체스판의 검정 부분
	public static void black(int x, int y, int cnt) {
		//x = 행, y = 열, cnt = 비숍의 수
		if (bishop < cnt) {
		 	bishop = cnt; //체스판의 검정색 부분에 비숍을 놓을 수 있는 최대 수
		}
		if (y >= chess.length) { 
			x += 1; //다음 행으로 넘김
			if (x % 2 == 1) //홀수면
				y = 1; //1부터 시작
			else //짝수면
				y = 0; //0부터 시작
		}
		if (x == chess.length) {
			return; 
		}
		if (chess[x][y] == 1 && isPossible(x, y)) {
			black(x, y + 2, cnt + 1);//비숍을 놓고 지나갈 경우
		}
		//비숍을 놓지 않고 지나갈 경우
		check[x][y] = false;
		black(x, y + 2, cnt);
		return;
	}
	
	//체스판의 흰 부분
	public static void white(int x, int y, int cnt) {
		//x = 행, y = 열, cnt = 비숍의 수
		if (bishop < cnt)
			bishop = cnt;//체스판의 흰색 부분에 비숍을 놓을 수 있는 최대 수
		if (y >= chess.length) {
			x += 1; //다음 행으로 넘김
			if (x % 2 == 1) //홀수면 
				y = 0; //0부터 시작
			else //짝수면 
				y = 1; //1부터 시작
		}
		if (x == chess.length) {
			return;
		}
		if (chess[x][y] == 1 && isPossible(x, y)) {
			white(x, y + 2, cnt + 1);//비숍을 놓고 지나갈 경우
		}
		//비숍을 놓지 않고 지나갈 경우
		check[x][y] = false;
		white(x, y + 2, cnt);
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int size = Integer.parseInt(st.nextToken());

		chess = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < size; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		check = new boolean[chess.length][chess.length]; 
		int answer = 0; //놓을 수 있는 비숍의 수
		black(0, 0, 0); //0, 0부터 시작
		answer = bishop; //검정색에 놓을 수 있는 비숍의 최대 개수
		bishop = 0;
		
		white(0, 1, 0); //0, 1부터 시작
		answer += bishop; //검정색에 놓을 수 있는 비숍의 최대 수 + 흰색에 놓을 수 있는 비숍의 최대 개수
		System.out.println(answer);
	}
}
