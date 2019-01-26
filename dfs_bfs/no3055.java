package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no3055 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static int escape(Queue<Pair> hedgehog, Pair exit, Queue<Pair> water, char[][] forest) {
		int x_pos[] = { 1, 0, -1, 0 };
		int y_pos[] = { 0, 1, 0, -1 };
		boolean check[][] = new boolean[forest.length][forest[0].length];
		int time = 0;
		
		while (!hedgehog.isEmpty()) {
			int water_size = water.size(); //물이 찬 곳의 수
			for (int i = 0; i < water_size; i++) {
				Pair w = water.poll();
				for (int k = 0; k < 4; k++) {
					int x = w.x + x_pos[k];
					int y = w.y + y_pos[k];
					if (x < 0 || y < 0 || x >= forest.length || y >= forest[0].length)
						continue;
					if (forest[x][y] == '.'||forest[x][y] == 'S') {	//물이 찰 수 있다면 
						forest[x][y] = '*';
						water.add(new Pair(x, y));
					}
				}
			}
			
			int size = hedgehog.size(); //고슴도치가 갈 수 있는  곳의 수
			for (int i = 0; i < size; i++) {
				Pair location = hedgehog.poll();
				if (exit.x == location.x && exit.y == location.y) 
					return time++; //탈출
				for (int k = 0; k < 4; k++) {
					int hed_x = location.x + x_pos[k];
					int hed_y = location.y + y_pos[k];
					if (hed_x < 0 || hed_y < 0 || hed_x >= forest.length || hed_y >= forest[0].length || check[hed_x][hed_y])
						continue;
					if (forest[hed_x][hed_y] == '.' || forest[hed_x][hed_y] == 'D') { //고슴도치가 이동 가능한 위치일 경우
						hedgehog.add(new Pair(hed_x, hed_y));
						check[hed_x][hed_y] = true;
					}
				}
			}
			time++;
		}
		return -1; //탈출 불가
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int R = Integer.parseInt(st.nextToken()); // 행
		int C = Integer.parseInt(st.nextToken()); // 열
		char forest[][] = new char[R][C];
		Pair exit = null; // 비버의 굴
		Queue<Pair> hedgehog = new LinkedList<>(); // 고슴도치 위치
		Queue<Pair> water = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				forest[i][j] = line.charAt(j);
				if (forest[i][j] == 'D')	//비버의 굴 위치
					exit = new Pair(i, j);
				else if (forest[i][j] == 'S') //고슴도치 위치
					hedgehog.add(new Pair(i, j));
				else if (forest[i][j] == '*') //물의 위치
					water.add(new Pair(i, j));
			}
		}
		
		int t = escape(hedgehog, exit, water, forest);
		if (t == -1) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(t);
		}
	}

}
