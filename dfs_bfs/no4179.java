package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no4179 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int R, C;
	static char map[][];
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static int Time = 0; // 탈출 시간
	static Queue<Pair> fire, jihun; 
	static void firing() {
		int fire_size = fire.size();
		for(int i = 0 ; i < fire_size ; i ++) {
			Pair f = fire.poll();
			for (int k = 0; k < 4; k++) {
				int x = f.x + x_pos[k];
				int y = f.y + y_pos[k];
				if (x < 0 || y < 0 || x >= R || y >= C)
					continue;
				if (map[x][y] =='.'||map[x][y] =='J') {
					map[x][y] = 'F';
					fire.add(new Pair(x, y));
				}
			}
		}
		
	}

	static int escape() {
		boolean visited[][] = new boolean[R][C]; //지훈이가 지나간 길
		while(!jihun.isEmpty()) {
			firing(); //불 확산
			int jihun_size = jihun.size();
			for(int i = 0 ; i < jihun_size ; i++) {
				Pair j = jihun.poll();
				if(j.x ==0|| j.y==0||j.x==R-1 ||j.y==C-1) //가장자리일 경우 탈출
					return Time++;
				for (int k = 0; k < 4; k++) {
					int x = j.x + x_pos[k];
					int y = j.y + y_pos[k];
					if (x < 0 || y < 0 || x >= R || y >= C || visited[x][y])
						continue;
					if(map[x][y]=='.') {
						visited[x][y] = true;
						jihun.add(new Pair(x, y)); //지훈이 이동
					}
				}
			}
			Time++;
		}
		return -1; //탈출 불가능
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); //행
		C = Integer.parseInt(st.nextToken()); //열
		map = new char[R][C];
		fire = new LinkedList<>(); //불의 위치
		jihun = new LinkedList<>(); //지훈이의 위치
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				char a = line.charAt(j);
				if(a == 'F')
					fire.add(new Pair(i, j));
				else if(a == 'J')
					jihun.add(new Pair(i, j));
				map[i][j] = a;
			}
		}
		
		if (escape()==-1)
			System.out.println("IMPOSSIBLE");
		else
			System.out.println(Time);

	}

}
