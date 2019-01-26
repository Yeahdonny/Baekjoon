package bellmanford_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class no11657 {
	static class Pair {
		public int start;
		public int end;
		public int time;

		Pair(int start, int end, int time) {
			this.start = start;
			this.end = end;
			this.time = time;
		}
	}

	static final int INF = 99999999;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		ArrayList<Pair> array = new ArrayList<Pair>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			while (st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				array.add(new Pair(a, b, c));
			}
		}

		int dist[] = new int[N + 1];
		for (int i = 2; i < N + 1; i++) {
			dist[i] = INF;
		}

		for (int i = 0; i < N - 1; i++) { 
			for (int j = 0; j < M; j++) {
				Pair pair = array.get(j);
				if (dist[pair.start] != INF && dist[pair.start] + pair.time < dist[pair.end]) {
					dist[pair.end] = dist[pair.start] + pair.time;
					System.out.println(pair.start + " " + pair.end + " " + pair.time + " " + dist[pair.end]);
				}
			}
		}

		// 사이클 확인, 위의 이중 for문 안에서 해결 가능 ㅜ
		boolean check = false;
		for (int j = 0; j < M; j++) {
			Pair pair = array.get(j);
			if (dist[pair.start] != INF && dist[pair.start] + pair.time < dist[pair.end]) {
				check = true;
				break;
			}
		}

		if (check) {
			System.out.println("-1");
		} else {
			for (int i = 2; i < N + 1; i++) {
				if (dist[i] == INF)
					System.out.println("-1");
				else
					System.out.println(dist[i]);
			}
		}
	}

}
/*
 * 3 3 1 2 3 2 1 -1000 2 1 5
 */