package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class no2178 {
	static class Pair{
		private int x;
		private int y;
		Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
		int getX() {
			return this.x;
		}
		int getY() {
			return this.y;
		}
	}
	
	static int map[][];
	public static void bfs(int N , int M) {
		int x[] = {0, 1, 0, -1};
		int y[] = {1, 0, -1, 0};
		int distance[][] = new int [N][M];
		Queue<Pair> q = new LinkedList<Pair>();
		boolean check [][] = new boolean[N][M];
		
		check[0][0] = true;
		q.offer(new Pair(0,0));
		distance[0][0] = 1;
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int px = p.getX();
			int py = p.getY();
			if(px == N-1 && py == M-1)break;
			for(int i = 0 ; i < 4 ; i ++) {
				int gox = px + x[i];
				int goy = py + y[i];
				if(gox < 0 || goy < 0 || gox >=N ||goy >=M)
					continue;
				
				if(map[gox][goy] == 1 && check [gox][goy] == false) {
					check[gox][goy] = true;
					q.offer(new Pair(gox, goy));
					distance[gox][goy] = distance [px][py] + 1;
				}
			}
		}
		System.out.println(distance[N-1][M-1]);
		/*
		for(int i = 0 ; i < N ; i ++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(distance[i][j]);
			}
			System.out.println();
		}
		*/
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M = scan.nextInt();
		map = new int[N][M];
		for(int i = 0 ; i < N ; i ++) {
			String line = scan.next();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = line.charAt(j)-'0';
			}
		}
		bfs(N,M);
		
	}

}
