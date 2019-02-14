package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no16236 {
	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int sharkSize = 2; //아기상어의 크기
	static int eatingTime = 0; //물고기를 잡아 먹을 수 있는 총 시간
	static int eatFish = 0; //먹은 물고기의 수
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };
	static int map[][]; //공간
	static Queue<Pair> shark; //상어의 위치

	public static boolean calDistance(Queue<Pair> fish) {

		int time = 999999999; //물고기를 먹으러 가는데 걸린 시간
		Pair eating = null;
		Pair sharkStart = shark.peek(); //아기상어의 처음 위치
		while (!fish.isEmpty()) {
			Pair fishLocation = fish.poll(); //먹을 수 있는 물고기의 위치
			shark = new LinkedList<>();
			shark.add(sharkStart);
			int distance[][] = new int[map.length][map[0].length]; //먹으러 가는데 걸리는 시간 계산
			boolean visited[][] = new boolean[map.length][map[0].length]; //방문 여부 체크
			
			while (!shark.isEmpty()) {
				Pair sharkLocation = shark.poll();
				visited[sharkLocation.x][sharkLocation.y] = true;
				if (sharkLocation.x == fishLocation.x && sharkLocation.y == fishLocation.y) { //아기상어의 위치와 먹을 수 있는 물고기의 위치가 같을 경우
					if (time > distance[fishLocation.x][fishLocation.y]) { 
						time = distance[fishLocation.x][fishLocation.y];
						eating = fishLocation; //먹을 물고기의 위치
					}
					break;
				}
				for (int i = 0; i < 4; i++) {
					int x = x_pos[i] + sharkLocation.x;
					int y = y_pos[i] + sharkLocation.y;
					if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || visited[x][y]|| (map[x][y] > sharkSize && map[x][y] != 9)) 
						continue;
					distance[x][y] = distance[sharkLocation.x][sharkLocation.y] + 1;
					visited[x][y] = true;
					shark.offer(new Pair(x, y));
				}
			}
		}
		if (eating!=null) { //먹었는지 안먹었는지 확인
			/* 이 처럼 먹을 수 있는 물고기가 있지만 아기상어가 못움직일 경우
			 * 4
			 * 9 3 0 0
			 * 3 0 0 1
			 * 0 0 0 0
			 * 0 0 0 0
			 * 
			 * */
			// 먹기
			map[eating.x][eating.y] = 0; //물고기를 먹었을 경우 해당 공간에 0 처리
			eatingTime += time; 
			++eatFish;
			if (eatFish == sharkSize) { //여태 먹었던 물고기 수와 아기 상어의 크기가 같을 경우
				++sharkSize;
				eatFish = 0;
			}
			shark = new LinkedList<>();
			shark.offer(new Pair(eating.x, eating.y)); // 아기상어의 위치 바꿔줌
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		shark = new LinkedList<>(); // 아기 상어의 위치
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					shark.add(new Pair(i, j));
			}
		}

		Queue<Pair> fish = new LinkedList<>(); // 먹을 수 있는 물고기의 위치
		while (true) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] < sharkSize && map[i][j] != 0 && map[i][j] != 9) { 
						fish.add(new Pair(i, j)); 
					}
				}
			}
			if (!fish.isEmpty()) {
				if (calDistance(fish)) // 아기상어가 움직일 수 없다면
					break;
			} else //먹을 수 있는 물고기가 없다면
				break;
		}
		System.out.println(eatingTime);
	}

}
