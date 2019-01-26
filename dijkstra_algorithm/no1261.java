package dijkstra_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class no1261 {
	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int wall;
		Pair(int x, int y, int wall) {
			this.x = x;
			this.y = y;
			this.wall = wall;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.wall > o.wall)
				return 1;
			else if (this.wall < o.wall)
				return -1;
			return 0;
		}

	}

	static int x_pos[] = { 1, 0, -1, 0 };
	static int y_pos[] = { 0, 1, 0, -1 };
	static final int INF = 9999999;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int M = Integer.parseInt(st.nextToken()); // 가로
		int N = Integer.parseInt(st.nextToken()); // 세로
		int maze[][] = new int[N + 1][M + 1]; //미로
		int dist[][] = new int[N + 1][M + 1]; 

		for (int i = 1; i < N + 1; i++) {
			String line = in.readLine();
			for (int j = 1; j < M + 1; j++) {
				maze[i][j] = line.charAt(j - 1) - '0';
				dist[i][j] = INF;
			}
		}
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.offer(new Pair(1, 1, 0));
		dist[1][1] = 0;
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			for (int i = 0; i < 4; i++) {
				int x = pair.x + x_pos[i];
				int y = pair.y + y_pos[i];
				if (x < 1 || y < 1 || x > N || y > M)
					continue;
				if (dist[x][y] == INF) {	
					dist[x][y] = Math.min(dist[x][y], dist[pair.x][pair.y] + maze[x][y]);
					q.offer(new Pair(x, y, dist[x][y]));
				}
			}
		}
		System.out.println(dist[N][M]);
	}
}
