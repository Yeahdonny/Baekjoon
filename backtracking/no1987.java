package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no1987 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int MAX = -1;
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static char board[][]; //보드
	static boolean alpha[] = new boolean[26]; //알파벳

	public static void dfs(Pair position, boolean[][] visited, int cnt) {
		for (int i = 0; i < 4; i++) {
			int x = position.x + x_pos[i];
			int y = position.y + y_pos[i];
			if (x < 0 || y < 0 || x >= visited.length || y >= visited[0].length || visited[x][y] || alpha[board[x][y]-'A'])
				continue;
			visited[x][y] = true;
			alpha[board[x][y]-'A'] = true;
			dfs(new Pair(x, y), visited, cnt + 1);
			visited[x][y] = false;
			alpha[board[x][y]-'A'] = false;
			

		}
		if (cnt > MAX)
			MAX = cnt;
		return;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = line.charAt(j);
			}
		}
		boolean visited[][] = new boolean[R][C]; //방문여부 확인
		alpha[board[0][0]-'A'] = true; //좌측 상단의 칸 포함
		dfs(new Pair(0, 0), visited, 1);
		System.out.println(MAX);
	}

}
