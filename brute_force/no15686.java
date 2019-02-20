package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no15686 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean visited[];
	static int INF = 99999999;
	static int min_dist = 99999999;// 도시의 치킨 거리의 최솟값
	static LinkedList<Pair> house, chicken;
	static int M;
	
	//치킨 거리 계산
	public static int cal(Pair a, Pair b) {
		return Math.abs(a.x-b.x) + Math.abs(a.y-b.y);
	}
	
	public static void distance(int total, int cnt) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < house.size(); i++) {
				int dist = INF;
				for (int j = 0; j < chicken.size(); j++) {
					if(!visited[j]) continue;
					dist = Math.min(dist, cal(house.get(i), chicken.get(j)));
				}
				sum += dist;
			}
			min_dist = Math.min(min_dist, sum);
			return;
		}
		if (total == chicken.size())
			return;	
		visited[total] = true;
		distance(total + 1, cnt + 1);
		visited[total] = false;
		distance(total + 1, cnt);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 도시 크기
		M = Integer.parseInt(st.nextToken()); //치킨집의 최대 개수
		house = new LinkedList<>(); //집 위치
		chicken = new LinkedList<>(); //치킨집 위치

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int a = Integer.parseInt(st.nextToken());
				if (a == 1) {
					house.add(new Pair(i, j));
				} else if (a == 2)
					chicken.add(new Pair(i, j));
			}
		}
		
		visited = new boolean[chicken.size()]; //영업과 폐업 여부
		distance(0, 0);
		System.out.println(min_dist);
	}
}
