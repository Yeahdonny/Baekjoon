package dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * 
 * dfs 메모리 문제 때문에
 * bfs 로 풀었음
 * 
 * 
 * */
public class no1697 {
	// ArrayList<Integer> route = new ArrayList<Integer>();
	public static void bfs(int X, int K) {
		boolean[] check = new boolean[100001];
		Queue<Integer> route = new LinkedList<Integer>();

		int time = 0;
		route.offer(X);
		check[X] = true;
		while(!route.isEmpty()) {
			int size = route.size();
			for(int i = 0 ; i < size;i++) {
				X = route.poll();
				if (X == K) {				//동생 찾음
					System.out.println(time);
					return;
				}
				if(X - 1 >= 0 && !check[X-1]) {
					route.offer(X-1);
					check[X-1] = true;
				}
				if(X + 1 <= 100000 && !check[X+1]) {
					route.offer(X+1);
					check[X+1] = true;
				}
				if(X*2 <= 100000 && !check[X*2]) {
					route.offer(X*2);
					check[X*2] = true;
				}
			}
			time++;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); // 수빈이 위치
		int K = scan.nextInt(); // 동생 위치
		bfs(N, K);
	}

}
