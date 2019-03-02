package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no14891 {
	static class Pair {
		int num, dir;

		Pair(int num, int dir) {
			this.num = num;
			this.dir = dir;
		}
	}
	static int gear[][];
	static boolean visited[];
	public static void moving(Queue<Pair> result) {
		while (!result.isEmpty()) {
			Pair pair = result.poll();
			if (pair.dir == 1) {// 시계방향 회전
				int a = gear[pair.num - 1][7];
				for (int i = 7; i > 0; i--) {
					gear[pair.num - 1][i] = gear[pair.num - 1][i - 1];
				}
				gear[pair.num - 1][0] = a;
			} else { //반시계방향 회전
				int a = gear[pair.num - 1][0];
				for (int i = 0; i < 7; i++) {
					gear[pair.num - 1][i] = gear[pair.num - 1][i + 1];
				}
				gear[pair.num - 1][7] = a;

			}
		}
	}

	public static void check(Pair p) {
		Queue<Pair> result = new LinkedList<>();
		Queue<Pair> rotate = new LinkedList<>();
		rotate.add(p);
		while (!rotate.isEmpty()) {
			Pair pair = rotate.poll();
			if (pair.num == 1) {// 1번 톱니바퀴
				visited[0] = true;
				if (gear[0][2] != gear[1][6] && visited[1] == false) { // 2번과 비교
					rotate.add(new Pair(2, pair.dir * -1));
				}
			} else if (pair.num == 2) { // 2번 톱니바퀴
				visited[1] = true;
				if (gear[1][2] != gear[2][6] && visited[2] == false) // 3번과 비교
					rotate.add(new Pair(3, pair.dir * -1));
				if (gear[0][2] != gear[1][6] && visited[0] == false) // 1번과 비교
					rotate.add(new Pair(1, pair.dir * -1));
			} else if (pair.num == 3) { // 3번 톱니바퀴
				visited[2] = true;
				if (gear[2][2] != gear[3][6] && visited[3] == false) // 4번과 비교
					rotate.add(new Pair(4, pair.dir * -1));
				if (gear[1][2] != gear[2][6] && visited[1] == false)// 2번과 비교
					rotate.add(new Pair(2, pair.dir * -1));
			} else if (pair.num == 4) {// 4번 톱니바퀴
				visited[3] = true;
				if (gear[3][6] != gear[2][2] && visited[2] == false)// 3번과 비교
					rotate.add(new Pair(3, pair.dir * -1));
			}
			result.add(new Pair(pair.num, pair.dir));
		}
		moving(result); //회전
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		gear = new int[4][8]; //톱니바퀴
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 4; i++) {
			String line = in.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = line.charAt(j) - '0';
			}
		}
		int K = Integer.parseInt(in.readLine());
		Queue<Pair> func = new LinkedList<>();
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()); //톱니바퀴 번호 
			int b = Integer.parseInt(st.nextToken()); //회전 방향
			func.add(new Pair(a, b));
		}
		int size = func.size();
		for (int i = 0; i < size; i++) {
			visited = new boolean[4];
			check(func.poll()); //현재 바퀴가 돌아갈 때 다른 바퀴도 돌아가는지 확인
		}
		
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += gear[i][0] * (int) Math.pow(2, i); //점수의 합
		}
		System.out.println(sum);
	}

}
