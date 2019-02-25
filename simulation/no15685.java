package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no15685 {
	static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean map[][] = new boolean[101][101];
	static int directions[] = new int[1024]; //10세대까지의 방향 모음
	public static void pattern() {
		directions[0] = 0; //처음에 0으로 시작
		for(int i = 1 ; i <= 10 ; i++) {
			//j = i세대일 때 마지막 index 값 (방향패턴이 대칭을 이루고 있음..) 
			for(int j = (int) Math.pow(2, i)-1, l= 0 ; j > l ;j--,l++) { 
				/*
				 * directions[l]값 이 0 일 때 : directions[j]에 들어갈 값 = 1
				 * directions[l]값 이 1 일 때 : directions[j]에 들어갈 값 = 2
				 * directions[l]값 이 2 일 때 : directions[j]에 들어갈 값 = 3
				 * directions[l]값 이 3 일 때 : directions[j]에 들어갈 값 = 0
				 * 
				 * */
				switch (directions[l]) {
				case 0:
					directions[j] = 1;
					break;
				case 1:
					directions[j] = 2;
					break;
				case 2:
					directions[j] = 3;
					break;
				case 3:
					directions[j] = 0;
					break;
				}
			}
		}
	}

	public static void draw(int x, int y, int d, int g) {
		map[x][y] =true;
		Pair position = new Pair(x, y); //끝 점
		int size = (int) Math.pow(2, g); //세대별 방향 수(라인 수)는 2^세대
		for (int i = 0; i < size; i++) {
			/* d별 direction
			 * d = 0 일 때 -> 0, 1, 2, 3
			 * d = 1 일 떄 -> 1, 2, 3, 0
			 * d = 2 일 때 -> 2, 3, 0, 1
			 * d = 3 일 때 -> 3, 0, 1, 2
			 * */
			int direction = directions[i] + d;
			if (direction >= 4) {
				direction -= 4;
			}
			
			
			if (direction == 0) {
				position.x += 1; //x좌표 증가
			} else if (direction == 1) {
				position.y += -1;//y좌표 감소
			} else if (direction == 2) {
				position.x += -1;//x좌표 감소
			} else if (direction == 3) {
				position.y += 1;//y좌표 증가
			}
			map[position.x][position.y] =true;
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		pattern(); //0 ~ 10세대 까지의 방향 패턴
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); // 시작 방향
			int g = Integer.parseInt(st.nextToken()); // 세대
			draw(x, y, d, g);
		}
		
		int cnt = 0;//정사각형의 개수
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (map[x][y] && map[x+1][y]&& map[x][y+1]&&map[x+1][y+1]) { //1x1정사각형을 이루는 경우
					++cnt;
				}
			}
		}
		System.out.println(cnt);
	}

}
