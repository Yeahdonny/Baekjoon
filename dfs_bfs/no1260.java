package dfs_bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class no1260 {
	public static void dfs(int[][]dmap, int check[], int V) {
		
	System.out.print(V + " ");
	check[V] = 1;
	for(int i=1;i<dmap.length; i++) {
		if(dmap[V][i] ==1 && check [i]==0) {
			dfs(dmap, check, i);
		}
	}
	}
	public static void bfs(int bmap[][], int check[], int V) {
		Queue<Integer> bq  = new LinkedList<Integer>();
		check[V] = 1;
		bq.offer(V);
		while(!bq.isEmpty()) {
			V = bq.poll();
			System.out.print(V+" ");
			for(int i = 1 ; i<bmap.length; i ++) {
				if(bmap[V][i]==1 && check [i]==0) {
					check[i]=1;
					bq.offer(i);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // 정점의 개수
		int M = scan.nextInt(); // 간선의 개수
		int V = scan.nextInt(); // 탐색을 시작할 정점의 번호
		int [][]map = new int[N+1][N+1];
		for(int i = 1 ; i <= M ;i++ ) { //간선의 수 만큼
			int x = scan.nextInt();
			int y = scan.nextInt();
			map[x][y] = 1;
			map[y][x] = 1;
		}
		int check[] = new int[N+1];	
		dfs(map, check, V);	
		System.out.println();
		check = new int[N+1];		
		bfs(map, check, V);
	}

}
