package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class no14889 {
	static int N;
	static boolean team[];
	static int people[][];
	static int min = 9999999;
	public static void sum() {
		LinkedList <Integer> start = new LinkedList<>(); //스타트 팀의 멤버
		LinkedList <Integer> link = new LinkedList<>(); //링크 팀의 멤버
		for(int i = 0 ; i < N ; i++) {
			if(team[i]) {
				start.add(i);
			}
			else link.add(i);
		}
		int sum_start = 0; //스타트 팀의 능력치
		int sum_link = 0; //링크 팀의 능력치
		
		for(int i = 0 ; i < start.size();i++) {
			int start_a = start.get(i);
			int link_a = link.get(i);
			for(int j = i+1 ; j< start.size();j++) {
				int start_b = start.get(j);
				int link_b = link.get(j);
				sum_start+=people[start_a][start_b]+people[start_b][start_a];
				sum_link+=people[link_a][link_b]+people[link_b][link_a];
			}
		}
		min = Math.min(min, Math.abs(sum_start-sum_link)); 
	}
	public static void div(int start, int cnt) {
		if(cnt==N/2) { //팀이 다 나누어진 경우
			sum(); //능력치 계산
			return;
		}
		for(int i = start; i < N ; i++ ) {
			team[i] = true; //스타트 팀으로
			div(i+1, cnt+1);
			team[i] = false; //링크 팀으로
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		people = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		team = new boolean[N];
		team[0] = true; //0번째 선수가 스타트 팀에 합류
		div(1, 1); //1번째 선수 부터 계산
		System.out.println(min);
	}

}
