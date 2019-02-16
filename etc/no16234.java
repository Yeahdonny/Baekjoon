package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no16234 {
	static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static int people[][]; // 나라별 인구 수
	static int L, R;
	static boolean visited[][]; // 국경선을 열었는지 확인하는 용
	static int migration[][]; // 변화 될 인구 수
	
	/*
	 * visited[][] : 전체적으로 크게 "이미 국경선을 열어" 다른 국가들과 연합을 이뤘었는지 확인하는 용 
	 * open[][] : "현재 확인하고 있는 국가들과" 연합을 이뤘는지 체크하는 배열
	 * 
	 */

	public static void combination(int i, int j, Queue<Pair> union) {
		boolean open[][] = new boolean[people.length][people.length]; // 이미 이 연합을 이루기로 결정한 국가
		int cnt = 1; // 연합을 이루고 있는 칸의 개수
		int population = people[i][j]; // 연합의 인구 수
		Queue<Pair> nation = new LinkedList<>(); // 연합을 이루는 나라들 좌표
		nation.add(new Pair(i, j));
		open[i][j] = true;
		visited[i][j] = true;
		while (!union.isEmpty()) {
			Pair pair = union.poll();
			nation.add(new Pair(pair.x, pair.y));
			++cnt;
			population += people[pair.x][pair.y];
			open[pair.x][pair.y] = true;
			for (int k = 0; k < 4; k++) {
				int x = pair.x + x_pos[k];
				int y = pair.y + y_pos[k];
				if (x < 0 || y < 0 || x >= people.length || y >= people.length || open[x][y])
					continue;
				int compare = Math.abs(people[pair.x][pair.y] - people[x][y]); // 두 나라의 인구 차이
				if (compare >= L && compare <= R) {
					union.offer(new Pair(x, y));
					open[x][y] = true;
					visited[x][y] = true;
				}
			}
		}
		while (!nation.isEmpty()) {
			Pair move = nation.poll();
			migration[move.x][move.y] = population / cnt; // 연합의 인구 수 / 연합을 이루고 있는 칸의 수
		}

	}
	public static void change() {
		for (int i = 0; i < people.length; i++) {
			for (int j = 0; j < people.length; j++) {
				if (migration[i][j] != 0) {
					people[i][j] = migration[i][j]; // 인구 이동
					migration[i][j] = 0; // 초기화
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		people = new int[N][N];
		migration = new int[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Pair> union; // 국경선이 열릴 예정인 국가
		int result = 0; // 인구 이동 횟수
		boolean check = false; // 인구 이동이 일어날지 확인 용
		while (true) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) // 이미 다른 나라들끼리 연합을 이룬 나라라면 패스
						continue;
					union = new LinkedList<>();
					for (int k = 0; k < 4; k++) {
						int x = i + x_pos[k];
						int y = j + y_pos[k];
						if (x < 0 || y < 0 || x >= N || y >= N)
							continue;
						int compare = Math.abs(people[i][j] - people[x][y]); // 두 나라의 인구 차이 계산
						if (compare >= L && compare <= R) {
							union.add(new Pair(x, y));
							visited[x][y] = true;
						}
					}
					if (!union.isEmpty()) { // i, j 좌표의 나라의 국경선이 열릴 예정
						combination(i, j, union); // 연합을 이룰 국가들 더 확인
						check = true;
					}
				}
			}
			if (check) { // 인구 이동이 일어나야 한다면
				change(); // 인구 이동
				++result; // 인구 이동 횟수 +1
				check = false;
				visited = new boolean[N][N];
			} else
				break;
		}
		System.out.println(result);
	}

}
