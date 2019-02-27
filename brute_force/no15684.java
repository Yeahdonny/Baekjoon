package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no15684 {
	static int result = 99999999;
	static boolean flag = false;
	public static boolean check(boolean[][] ladder) {
		for (int line = 1; line < ladder[0].length; line++) {
			int i = line; //세로선
			for (int j = 1; j < ladder.length; j++) {
				if (ladder[j][i])//우측 이동 
					i += 1;
				else if (i - 1 > 0 && ladder[j][i - 1]) // 좌측 이동
					i -= 1;
			}
			if (i != line) //일치 하지 않으면
				return false;
		}
		return true; //더 이상 사다리 추가 안해도 됨
	}

	public static void Ladders(boolean[][] ladder, int cnt, int add, int start) {
		if (flag) //더 이상 계산 안해도 욈
			return;
		if (cnt == add) { 
			if (check(ladder)) {//i번 세로선의 결과가 i번에 나온다면
				result = cnt; //추가한 가로선의 수
				flag = true;
			}
			return;
		}
		// 사다리 추가
		for (int i = start; i < ladder.length; i++) {
			for (int j = 1; j < ladder[0].length - 1; j++) {
				if (ladder[i][j])
					continue;
				if (ladder[i][j - 1] == false && ladder[i][j + 1] == false) {
					ladder[i][j] = true;//사다리 추가
					Ladders(ladder, cnt + 1, add,i);
					ladder[i][j] = false;
				}
			}
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 세로선의 개수
		int M = Integer.parseInt(st.nextToken()); // 가로선의 개수
		int H = Integer.parseInt(st.nextToken()); // 세로선마다 가로선을 놓을 수 있는 위치 개수
		boolean ladder[][] = new boolean[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			ladder[a][b] = true;
		}
		for (int i = 0; i < 4; i++) { //추가할 사다리의 개수가 0 ~ 3개 일 때 가능한지만 체크 
			Ladders(ladder, 0, i,1);
			if (flag) //성공했다면
				break;
		}
		if (result == 99999999) //추가해야할 사다리의 개수가 4개 이상일 때
			result = -1;
		System.out.println(result);
	}
}
