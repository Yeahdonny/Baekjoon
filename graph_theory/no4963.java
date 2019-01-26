package graph_theory;


import java.util.Scanner;


//재귀함수 쓰면서 이어진 섬들에 마크 표시해서 if문을 통과하는 횟수를 섬의 수라고 체크함

public class no4963 {
	
	static int []x= {-1, 0, 1, 1, 1, 0, -1, -1};	//대각선 왼쪽 위부터 시계방향
	static int []y = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int weight;
	static int height;
	static int [][]map = new int[50][50];
	public static void countLand(int h, int w) { // h = y, w = x; (y, x)
		map[h][w] = -1;//확인한 곳을 다르게 표시하여 다음번에 여기를 또 체크하는 일이 없도록 함
		int hy = 0;
		int wx = 0;
		for(int i = 0 ; i < 8 ; i ++) { //넘겨온 좌표값 근처에 섬이 있는지 확인
			hy = h + y[i];	
			wx = w + x[i];	
		
			if(hy >= height ||hy < 0|| wx >= weight ||wx<0) { //기존의 섬 크기를 벗어나지 않기 위해 조건 걸어 둠
				continue;
			}
			
			if(map[hy][wx] == 1) {	//근방에 섬이 있다면
				countLand(hy, wx);	//그 섬의 근처에 또 섬이 없는지 체크
									//이걸 계속 반복 하면 이어진 섬을 다 방문하면서 값을 -1로 변경하여 하나의 섬으로 count 가능
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		StringBuffer sb = new StringBuffer();
		while(true) {
			weight = scan.nextInt();	//너비 입력받음
			height = scan.nextInt();	//높이 입력받음
			if(weight == 0 && height ==0)break; //0, 0 입력받으면 종료
		/*
		//map 초기화를 했지만 사방팔방 확인할 때 조건만 잘 처리해준다면 초기화 안해도 됨 
			for(int i= 0 ;i<50;i++) {
				for(int j = 0; j < 50;j++) {
					map[i][j]=0;
				}
			}
		*/	
		// 바다와 섬을 입력받음
		for(int i = 0; i < height; i ++) {
			for(int j = 0 ; j < weight ; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		
		
		int count = 0;
		
		for(int i = 0; i <height;i++) {
			for(int j = 0; j < weight ; j++) {
				if(map[i][j]==1) {
					countLand(i, j);	//이어진 섬을 확인
					count++;			//재귀호출 다 돌고 오면 count++
				}
			}
		}
		sb.append(count+"\n");	//0 0을 입력받고 난 후 모든 것을 출력해야 되기에 string buffer를 사용하여 문자열 추가해줌
	}
		System.out.println(sb);
	}

}
