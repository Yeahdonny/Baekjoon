package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no5373 {
	static class Pair {
		char side;//기준 면
		boolean direction; //true = 시계방향, false = 반시계방향

		Pair(char side, boolean direction) {
			this.side = side;
			this.direction = direction;
		}
	}
	static char up[][], down[][], front[][], back[][], left[][], right[][];
	static int x_pos[] = { 0, 1, 0, -1 };
	static int y_pos[] = { 1, 0, -1, 0 };

	public static char[][] rightmoving(char[][] moving) {//해당 면 시계방향으로 회전
		Queue<Character> color = new LinkedList<>();
		for (int i = 0; i < 3; i++) {
			for (int j = 2; j >= 0; j--) {
				color.add(moving[j][i]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				moving[i][j] = color.poll();
			}
		}
		return moving;
	}

	public static char[][] leftmoving(char[][] moving) {//해당 면 반시계방향으로 회전
		Queue<Character> color = new LinkedList<>();
		for (int i = 2; i >= 0; i--) {
			for (int j = 0; j < 3; j++) {
				color.add(moving[j][i]);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				moving[i][j] = color.poll();
			}
		}
		return moving;
	}

	public static void turnLeft(boolean direction) { //왼쪽 면
		if (direction) { // 시계
			left = rightmoving(left);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = down[i][0];
				down[i][0] = front[i][0];
				front[i][0] = up[i][0];
				up[i][0] = back[j][2];
				back[j][2] = a;
			}
		} else {// 반시계방향
			left = leftmoving(left);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = back[j][2];
				back[j][2] = up[i][0];
				up[i][0] = front[i][0];
				front[i][0] = down[i][0];
				down[i][0] = a;
			}
		}
	}

	public static void turnRight(boolean direction) { //오른쪽 면
		if (direction) {//시계방향
			right = rightmoving(right);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = back[j][0];
				back[j][0] = up[i][2];
				up[i][2] = front[i][2];
				front[i][2] = down[i][2];
				down[i][2] = a;
			}
		} else {// 반시계방향
			right = leftmoving(right);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = down[i][2];
				down[i][2] = front[i][2];
				front[i][2] = up[i][2];
				up[i][2] = back[j][0];
				back[j][0] = a;
				
			}
		}
	}

	public static void turnFront(boolean direction) {//앞면
		if (direction) {//시계방향
			front = rightmoving(front);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = left[j][2];
				left[j][2] = down[0][j];
				down[0][j] = right[i][0];
				right[i][0] = up[2][i];
				up[2][i] = a;
			}
		} else {// 반시계방향
			front = leftmoving(front);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = up[2][i];
				up[2][i] = right[i][0];
				right[i][0] = down[0][j];
				down[0][j] = left[j][2];
				left[j][2] = a;
			}
		}
	}

	public static void turnBack(boolean direction) {//뒷면
		if (direction) { // 시계방향
			back = rightmoving(back);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = down[2][i];
				down[2][i] = left[i][0];
				left[i][0] = up[0][j];
				up[0][j] = right[j][2];
				right[j][2] = a;
			}

		} else {// 반시계방향
			back = leftmoving(back);
			for (int i = 0, j = 2; i < 3; i++, j--) {
				char a = right[j][2];
				right[j][2] = up[0][j];
				up[0][j] = left[i][0];
				left[i][0] = down[2][i];
				down[2][i] = a;
			}
		}
	}

	public static void turnUp(boolean direction) { //윗면
		if (direction) {//시계방향
			up = rightmoving(up);
			for (int i = 0; i < 3; i++) {
				char a = right[0][i];
				right[0][i] = back[0][i];
				back[0][i] = left[0][i];
				left[0][i] = front[0][i];
				front[0][i] = a;
			}

		} else {// 반시계방향
			up = leftmoving(up);
			for (int i = 0; i < 3; i++) {
				char a = front[0][i];
				front[0][i] = left[0][i];
				left[0][i] = back[0][i];
				back[0][i] = right[0][i];
				right[0][i] = a;
			}
		}
	}

	public static void turnDown(boolean direction) { //아랫면
		if (direction) {//시계방향
			down = rightmoving(down);
			for (int i = 0; i < 3; i++) {
				char a = left[2][i];
				left[2][i] = back[2][i];
				back[2][i] = right[2][i];
				right[2][i] = front[2][i];
				front[2][i] = a;
			}
		} else {// 반시계방향
			down = leftmoving(down);
			for (int i = 0; i < 3; i++) {
				char a = front[2][i];
				front[2][i] = right[2][i];
				right[2][i] = back[2][i];
				back[2][i] = left[2][i];
				left[2][i] = a;
			}
		}
	}

	public static void turning(Queue<Pair> turn) {
		while (!turn.isEmpty()) {
			Pair p = turn.poll();
			switch (p.side) {
			case 'L':
				turnLeft(p.direction);
				break;
			case 'R':
				turnRight(p.direction);
				break;
			case 'U':
				turnUp(p.direction);
				break;
			case 'D':
				turnDown(p.direction);
				break;
			case 'F':
				turnFront(p.direction);
				break;
			case 'B':
				turnBack(p.direction);
				break;
			}
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(in.readLine());
		for (int test = 0; test < testCase; test++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(in.readLine(), " ");
			Queue<Pair> turn = new LinkedList<Pair>();
			for (int i = 0; i < n; i++) {
				String word = st.nextToken();
				char side = word.charAt(0);
				if (word.charAt(1) == '+')
					turn.add(new Pair(side, true));
				else
					turn.add(new Pair(side, false));
			}
			up = new char[3][3];
			down = new char[3][3];
			front = new char[3][3];
			back = new char[3][3];
			left = new char[3][3];
			right = new char[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					up[i][j] = 'w'; //흰색
					down[i][j] = 'y'; //노란색
					front[i][j] = 'r'; //빨간색
					back[i][j] = 'o'; //오렌지색
					left[i][j] = 'g'; //초록색
					right[i][j] = 'b'; //파란색
				}
			}
			turning(turn);
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(up[i][j]);
				}
				System.out.println();
			}
		}
	}
}
