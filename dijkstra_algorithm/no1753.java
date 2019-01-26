package dijkstra_algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

class Pair implements Comparable<Pair> {
	public int v;
	public int w;

	Pair(int v, int w) {
		this.v = v; // 도착점
		this.w = w; // 가중치
	}

	@Override
	public int compareTo(Pair pair) {	//가장 낮은 가중치를 우선으로 (PriorityQueue)
		// TODO Auto-generated method stub
		if (this.w > pair.w)
			return 1;
		else if (this.w < pair.w)
			return -1;
		return 0;
	}
}

public class no1753 {

	static final int INF = 9999999;

	@SuppressWarnings("unchecked") // 빡쳐서 넣음 ArrayList<Pair> 이 라인에 계속 노란줄 떠서 시발
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int V = scan.nextInt(); // 정점의 개수
		int E = scan.nextInt(); // 간선의 개수
		int K = scan.nextInt(); // 시작 정점 번호
		int weight[] = new int[V + 1]; // 시작점에서 해당 정점(인덱스)까지의 최단거리
		boolean visit[] = new boolean[V + 1]; // 정점 방문여부
		ArrayList<Pair>[] route = new ArrayList[V + 1];
		for (int i = 1; i < V + 1; i++) {
			route[i] = new ArrayList<Pair>();
			if (i == K)
				continue;
			weight[i] = INF;
		}
		for (int i = 0; i < E; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();
			int w = scan.nextInt();
			route[u].add(new Pair(v, w));
		}
		/*
		 * 테스트 용
		 * 2 3 
		 * 1 
		 * 1 2 3
		 * 1 2 1 
		 * 1 2 4
		 * 
		 */
		
		
	
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.offer(new Pair(K, 0));
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			System.out.println(pair.v+" "+pair.w);
			if (visit[pair.v] == true)
				continue;
			visit[pair.v] = true; //route
			/*
			 * ArrayList<Pair> arrayList = route[pair.v]; 
			 * for (int i = 0; i < arrayList.size(); i++) { Pair p = arrayList.get(i); }
			 * 
			 */
			for (Pair p : route[pair.v]) { //route[pair.v] 값들을 p 변수에 하나하나씩 대입
				if (visit[p.v] == false) {
					weight[p.v] = Math.min(weight[p.v], weight[pair.v] + p.w);
					q.add(new Pair(p.v, weight[p.v]));
				}
			}

		}

		for (int i = 1; i < V + 1; i++) {
			if (weight[i] == INF) {
				System.out.println("INF");
				continue;
			}
			System.out.println(weight[i]);
		}
	}

}
