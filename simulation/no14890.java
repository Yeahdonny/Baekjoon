package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no14890 {
	static int road[][];
	static int L;
	static boolean check[];
	//가로행 검사 (낮은 칸을 만난 경우)
	public static boolean Rlow(int standard, int startx, int starty) {
		int k = 0;
		for( ; k < L ; k++) { //경사로를 둘 수 있는지 검사
			if(starty+k<road.length&&standard==road[startx][starty+k]) {
				check[starty+k]=true;
			}else {
				//경사로를 둘 수 없다 판단될 경우, 돌아가는 함수
				for(int back = k; back>=starty ; back--) {
					check[back] = false;
				}
				return false;
			}
		}
		return true;		
	}
	//가로행 검사(높은 칸을 만난 경우)
	public static boolean Rhigh(int standard, int startx, int starty) {
		int k = 0 ; 
		for(;k<L; k++) { //경사로를 둘 수 있는지 검사
			if(starty-k >=0 && check[starty-k] )return false; //경사로를 겹쳐서 놓을 수 없음
			if(starty-k >=0&&standard==road[startx][starty-k]  ) {
				check[starty-k]=true; 
			}else {
				//돌아가는 함수
				for(int back = k ; back<=starty ; back++) {
					check[back] = false;
				}
				return false;
			}
		}
		return true;
	}
	//세로열 검사(낮은 칸을 만난 경우)
	public static boolean Clow(int standard, int startx, int starty) {
		int k = 0;
		for( ; k < L ; k++) {//경사로를 둘 수 있는지 검사
			if(startx+k<road.length&&standard==road[startx+k][starty]) {
				check[startx+k]=true;
			}else {
				//돌아가는 함수
				for(int back = k; back>=startx ; back--) {
					check[back] = false;
				}
				return false;
			}
		}
		return true;		
	}
	//세로열 검사(높은 칸을 만난 경우)
	public static boolean Chigh(int standard, int startx, int starty) {
		int k = 0 ; 
		for(;k<L; k++) {//경사로를 둘 수 있는지 검사
			if(startx-k >=0 &&check[startx-k] )return false; //경사로를 겹쳐서 놓을 수 없음
			if(startx-k >=0&&standard==road[startx-k][starty]  ) {
				check[startx-k]=true;
			}else { 
				//돌아가는 함수
				for(int back = k ; back<=startx ; back++) {
					check[back] = false;
				}
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		road = new int[N][N];
		for(int i = 0 ; i < N  ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		//가로행 검사
		for(int i = 0 ; i < N ; i++) {
			int comp = road[i][0]; //비교 값
			check = new boolean[N];
			int j = 0 ;
			for(; j < N ; j++) {
				if(comp != road[i][j]) {
					//값이 작아진 경우
					if(comp-road[i][j]==1) {//차이가 1
						if(Rlow(road[i][j], i, j)) 
							comp = road[i][j];
						else break;
					}
					//값이 커진 경우
					else if(comp-road[i][j]==-1) { //차이가 1
						if(Rhigh(road[i][j-1], i, j-1))
							comp = road[i][j];
						else break;
					}
					else break;
				}
			}
			if(j==N) {//길을 지나가게된 경우
				cnt++;
			}
		}
		//세로열 검사
		for(int j = 0; j< N ; j++) {
			int comp = road[0][j]; //비교 값
			check = new boolean[N]; //해당 칸에 경사로를 두었는지 판단하는 배열
			int i = 0 ;
			for(; i < N ; i++) {
				if(comp != road[i][j]) {
					//값이 작아진 경우
					if(comp-road[i][j]==1) {//차이가 1
						if(Clow(road[i][j], i, j)) 
							comp = road[i][j];
						else break;
					}
					//값이 커진 경우
					else if(comp-road[i][j]==-1) {//차이가 1
						if(Chigh(road[i-1][j], i-1, j))
							comp = road[i][j];
						else break;
					}
					else break;
				}
			}
			if(i==N) { //길을 지나가게된 경우
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
