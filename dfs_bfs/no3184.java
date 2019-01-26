package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no3184 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static boolean check[][];
	static int R, C;
	static char yard[][];
	static int sheep = 0; //살아남은 양
	static int wolf = 0; //살아남은 늑대
	static void bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<Pair>();
		int o = 0, v = 0;// o = 양, v = 늑대
		check[i][j] = true;
		if (yard[i][j] == 'o') o++;
		else if (yard[i][j] == 'v')	v++;
		
		q.offer(new Pair(i, j));
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			for (int k = 0; k < 4; k++) {
				int x = pair.x + x_pos[k];
				int y = pair.y + y_pos[k];
				if (x < 0 || y < 0 || x >= R || y >= C)
					continue;
				if (check[x][y] == false && yard[x][y] != '#') {
					if (yard[x][y] == 'o') o++;
					else if (yard[x][y] == 'v') v++;
					check[x][y] = true;
					q.offer(new Pair(x, y));
				}

			}
		}
		if (v >= o)
			wolf += v;
		else
			sheep += o;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 행
		C = Integer.parseInt(st.nextToken()); // 열
		yard = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				yard[i][j] = line.charAt(j);
			}
		}
		check = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (yard[i][j] != '#' && check[i][j] == false) {
					bfs(i, j);	
				}
			}
		}

		System.out.println(sheep + " " + wolf);

	}

}
