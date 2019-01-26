package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class no7576 {
	static class Pair{
		private int x;
		private int y;
		Pair(int x, int y){
			this.x= x ;
			this.y=y;
		}
		int getX() {return this.x;}
		int getY() {return this.y;}
		
	}
	static int box[][];
	public static void bfs(int box[][], int M, int N) {
		Queue <Pair> q = new LinkedList<>();
		int x[] = {0, 1, 0, -1};
		int y[] = {1, 0, -1, 0};
		//q.offer(new Pair(0,0));
		boolean check [][] = new boolean[N][M];
		
		for(int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j++) {
				if(box[i][j]==1)
					q.offer(new Pair(i, j));
			}
		}
		
		int count = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s = 0 ; s < size ; s++) {
			Pair pair = q.poll();
			for(int i = 0 ; i < 4 ; i++) {
				int goX = pair.getX() + x[i];
				int goY = pair.getY() + y[i];
				System.out.println(pair.getX()+" "+ pair.getY());
				if(goX < 0 || goY < 0 || goX >= N || goY >= M)
					continue;
				if(box[goX][goY] == 0 && check[goX][goY] == false) {
					box[goX][goY] = 1;
					q.offer(new Pair(goX, goY));
					check[goX][goY] = true;
				}
				}
			}
			count++;
		}
		if(checkZero(box, M, N))
			System.out.println(count-1);
		else
			System.out.println(-1);
	}
	
	//상자에 덜 익은 토마토가 있는지 확인 
	public static boolean checkZero(int box[][], int M, int N) {
		for(int i = 0 ; i < N ; i ++) {
			for (int j = 0 ; j < M ; j++) {
				if(box[i][j]==0) return false;
			}
		}
		System.out.println("ok");
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int M = scan.nextInt(); // 상자의 가로 칸의 수
		int N = scan.nextInt(); // 상자의 세로 칸의 수
		box = new int[N][M]; // 상자
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j ++) {
				box[i][j] = scan.nextInt();
			}
		}
		
		if(checkZero(box, M, N)) {
			System.out.println(0);
			return;
		}else {
			bfs(box, M, N);
		}
		
		
	}

}
