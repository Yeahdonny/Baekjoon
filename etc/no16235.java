package etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class no16235 {
	static class Pair {
		int x, y; //좌표
		int age; //나무의 나이
		Pair(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;

		}
	}
	static Queue<Pair> Fyears = new LinkedList<>(); //번식할 나무
	static Queue<Pair> death = new LinkedList<>(); //죽게되는 나무
	static LinkedList<Pair> tree[]; //심겨진 나무
	static int land[][]; //땅 (누적된 양분)
	public static void spring() {
		for (int i = 1; i < tree.length; i++) {
			if (!tree[i].isEmpty()) {
				int size = tree[i].size();
				for (int k = 0; k < size; k++) {
					Pair pair = tree[i].poll();
					if (land[pair.x][pair.y] < pair.age) { //나이만큼 양분을 먹을 수 없는 경우
						death.add(new Pair(pair.x, pair.y, pair.age)); //죽게되는 나무에 추가
					} else {
						land[pair.x][pair.y] -= pair.age;  //나이만큼 양분을 먹게됨으로 나이만큼 빼줌
						pair.age += 1; //나이 + 1
						tree[i].addLast((new Pair(pair.x, pair.y, pair.age))); //나이가 어린 나무부터 양분을 먹을 수 있도록 하기 위해 마지막에 집어넣어줌 
						if (pair.age % 5 == 0) { //5의 배수 일 경우
							Fyears.add(new Pair(pair.x, pair.y, pair.age)); //번식할 나무에 추가
						}
					}
				}
			}
		}
		//여름
		while (!death.isEmpty()) { 
			Pair pair = death.poll();
			land[pair.x][pair.y] += pair.age / 2; //(나이/2) 한 값을 양분으로 추가
		}
	}
	
	//가을
	public static void fall() {
		int x_pos[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
		int y_pos[] = { 1, 1, 0, -1, -1, -1, 0, 1 };
		while (!Fyears.isEmpty()) {
			Pair pair = Fyears.poll();
			for (int l = 0; l < 8; l++) {
				int x = pair.x + x_pos[l];
				int y = pair.y + y_pos[l];
				if (x < 1 || y < 1 || x >= tree.length || y >= tree.length)
					continue;
				tree[x].addFirst((new Pair(x, y, 1))); //8방으로 나이가 1인 나무 심기 (가장 어린 나이의 나무이기 때문에 앞에 넣어줌)
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); //땅의 크기
		int M = Integer.parseInt(st.nextToken()); //처음에 심은 나무의 수
		int K = Integer.parseInt(st.nextToken()); //K년
		int A[][] = new int[N + 1][N + 1]; // 추가되는 양분의 양
		land = new int[N + 1][N + 1];
		tree = new LinkedList[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			tree[i] = new LinkedList<Pair>();
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				land[i][j] = 5; //기본 양분
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()); 
			int y = Integer.parseInt(st.nextToken()); //위치  (x, y)
			int z = Integer.parseInt(st.nextToken()); //나무의 나이
			tree[x].add(new Pair(x, y, z));
		}
		int treeNumber= 0; //살아남은 나무의 수
		for (int i = 0; i < K; i++) {
			spring(); //봄
			fall(); //가을
			treeNumber = 0;
			for (int a = 1; a < A.length; a++) {
				if (!tree[a].isEmpty() && i==(K-1)) {
					int size = tree[a].size();
					treeNumber += size;
				}
				//겨울
				for (int b = 1; b < A.length; b++) {
					land[a][b] += A[a][b]; //땅에 양분을 추가
				}
			}
		}
		System.out.println(treeNumber);
	}

}
