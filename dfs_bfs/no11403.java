package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class no11403 {


	static void dfs(int V, int[][] G, int []check) {
		for (int j = 0; j < G.length; j++) {
			if (G[V][j] == 1 && check[j] == 0) {
				check[j] = 1;
				dfs(j, G, check);
			}
		}

	}

	static void bfs(int V, int[][] G, int[] check) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(V);
		while (!q.isEmpty()) {
			V = q.poll();
			for (int j = 0; j < G.length; j++) {
				if (G[V][j] == 1 && check[j] == 0) {
					check[j] = 1;
					q.offer(j);
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int G[][] = new int[N][N];
		int[][] result = new int[N][N];
		int check[];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				G[i][j] = scan.nextInt();
			}
		}

		for (int V = 0; V < N; V++) {
			check = new int[N];
			dfs(V, G, check);
			for (int i = 0; i < N; i++) {
				result[V][i] = check[i];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(result[i][j] + " ");
			}
			System.out.println();
		}

	}
}
