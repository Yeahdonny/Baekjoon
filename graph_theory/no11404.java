package graph_theory;

import java.util.Scanner;

public class no11404 {
	static final int INF = 100001;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt(); //도시의 개수
		int M = scan.nextInt(); //버스의 개수
		int route[][] = new int[N+1][N+1];
		
		for(int i = 0 ; i < M ; i++) {
			int a = scan.nextInt(); //출발지
			int b = scan.nextInt(); //도착지
			int c = scan.nextInt(); //비용
			if(route[a][b]!=0 && route[a][b] < c ) continue;
			route[a][b] = c;
		}
		
		for(int i = 1 ; i < N+1 ; i++) {
			for(int j = 1 ; j < N+1 ; j++) {
				if(i==j)continue;
				else if(route[i][j]==0) 
					route[i][j] =INF;
			}
		}
		
		
		for(int k = 1 ; k < N+1 ; k++) {	//거치는 점
			for(int i = 1 ; i < N+1 ; i++) {	//출발
				for(int j = 1 ; j < N+1 ; j++) {	//도착
					if(i==j)continue;
					//System.out.println(k+" "+i+" "+j+" : "+route[i][j]+" "+route[i][k]+" "+route[k][j]);
					if(route[i][j] > route[i][k] + route[k][j]) { //안거치고 바로 가는 것과 k를 거쳐 도착점까지 가는 것 비교
						//System.out.println("change!");
						route[i][j] = route[i][k] + route[k][j]; //거쳐서 가는 것이 더 저렴한 경우
					}
				}
			}
		}
		
		for(int i = 1 ; i < N +1 ; i++) {
			for(int j = 1 ; j < N+1 ; j++) {
				if(route[i][j] == INF) {
					System.out.print("0 ");
					continue;
				}
				System.out.print(route[i][j]+" ");
			}
			System.out.println();
		}
		
		
	}

}
