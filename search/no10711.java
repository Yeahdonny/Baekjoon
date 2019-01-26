package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no10711 {
	static class Pair {
		int x, y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int H, W;
	static int x_pos[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int y_pos[] = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static Queue<Pair> sandQ = new LinkedList<>(); //무너질 모래성의 좌표
	static char sand[][]; //모래성의 강도
	static int noSand[][]; //모래성 근처 '.' 의 수 ('.' : 모래가 없다는 뜻)
	static boolean check[][]; //모양이 변화된, 변화 예정인 모래성 
	static int wave = 0; //파도 수

	static void checkSand() {
		noSand = new int[H][W];
		check = new boolean[H][W];
		for (int i = 1; i < H - 1; i++) { //모래성은 격자의 가장자리와 접해있지 않기 때문에 -1
			for (int j = 1; j < W - 1; j++) {
				if (sand[i][j] != '.' && sand[i][j] - '0' < 9) { // 모래성의 강도가 9이상일 경우 모양이 변형될 일 없음
					for (int k = 0; k < 8; k++) { //8방으로 '.' 의 수 확인
						int x = i + x_pos[k];
						int y = j + y_pos[k];
						if (x < 0 || y < 0 || x >= H || y >= W) continue;
						if (sand[x][y] == '.') { 
							noSand[i][j]++; //근처가 '.' 일 경우 ++
						}
					}
					if (noSand[i][j] >= sand[i][j] - '0') { //모래성이 쌓여있지 않은 개수가 모래성의 강도보다 많거나 같은 경우 
						sandQ.add(new Pair(i, j)); 
						check[i][j] = true;
					}
				}
			}
		}
		if (!sandQ.isEmpty()) { 
			reSand();
		}
	}

	static void reSand() {
		while (!sandQ.isEmpty()) { //더 이상 무너질 모래성이 없어질 떄 까지
			int size = sandQ.size(); //파도 한번에 무너질 모래성의 수
			wave++;
			for (int i = 0; i < size; i++) { 
				Pair pair = sandQ.poll();
				sand[pair.x][pair.y] = '.'; //모래성이 무너진 것으로 형태 변경
				for (int k = 0; k < 8; k++) { //무너진 모래성의 8방으로 모래성이 쌓여 있는 곳을 확인
					int x = pair.x + x_pos[k];
					int y = pair.y + y_pos[k];
					if (x < 0 || y < 0 || x >= H || y >= W) continue;
					if (sand[x][y] != '.' && sand[x][y] - '0' < 9 && check[x][y] == false) { //강도가 9 미만이며 아직 변화되거나 변화 예정이 아닌 모래성 
						noSand[x][y]++; //무너진 모래성의 8방 내에 위치하여 '.' 의 수 ++
						if (noSand[x][y] == sand[x][y] - '0') { //이 때, 모래성이 쌓여있지 않은 개수가 모래성의 강도보다 많거나 같아질 경우
							sandQ.add(new Pair(x, y)); //무너질 모래성 좌표에 추가
							check[x][y] = true; 
						}
					}
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		H = Integer.parseInt(st.nextToken()); // 가로
		W = Integer.parseInt(st.nextToken()); // 세로
		sand = new char[H][W];
		for (int i = 0; i < H; i++) {
			String line = in.readLine();
			for (int j = 0; j < W; j++) {
				sand[i][j] = line.charAt(j);
			}
		}
		checkSand();
		System.out.println(wave);
	}

}
