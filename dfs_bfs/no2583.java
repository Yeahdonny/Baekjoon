package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no2583 {
	static int paper[][];

	static void makeRec(int x1, int y1, int x2, int y2) {
		for (int i = x1; i < x2; i++) { //여기랑
			for (int j = y1; j < y2; j++) { //여기도 <=
				paper[i][j] = 1;
			}
		}
	}

	static int[] x_pos = { 1, 0, -1, 0 };
	static int[] y_pos = { 0, 1, 0, -1 };
	static int size = 0;

	static void dfs(int x, int y) {
		size++;
		paper[x][y] = -1;
		for (int i = 0; i < 4; i++) {
			int goX = x + x_pos[i];
			int goY = y + y_pos[i];
			if (goX < 0 || goX >= paper.length || goY < 0 || goY >= paper[0].length) // < 1 로 했었음
				continue;
			if (paper[goX][goY] == 0)
				dfs(goX, goY);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		paper = new int[N][M];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			while (st.hasMoreTokens()) {
				int x1 = Integer.parseInt(st.nextToken());
				int y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken());
				int y2 = Integer.parseInt(st.nextToken());
				//makeRec(y1 + 1, x1 + 1, y2, x2);
				makeRec(x1, y1, x2, y2);
			}
		}

		int count = 0;
		int area[] = new int[N * M];
		for (int i = 0; i < paper.length; i++) {
			for (int j = 0; j < paper[1].length; j++) {
				if (paper[i][j] == 0) {
					dfs(i, j);
					area[count] = size;
					size = 0;
					count++;
				}
			}
		}

		System.out.println(count);
		Arrays.sort(area);
		for (int i = area.length - count; i < area.length; i++) {
			System.out.print(area[i] + " ");
		}
	}

}
