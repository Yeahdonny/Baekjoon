package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class no1149 {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int home[][] = new int[N][3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			while (st.hasMoreTokens()) {
				home[i][0] = Integer.parseInt(st.nextToken());//빨
				home[i][1] = Integer.parseInt(st.nextToken());//파
				home[i][2] = Integer.parseInt(st.nextToken());//초
			}
		}
		
		for (int i = N - 2; i >= 0; i--) {
			home[i][0] += Math.min(home[i + 1][1], home[i + 1][2]);  
			home[i][1] += Math.min(home[i + 1][0], home[i + 1][2]);
			home[i][2] += Math.min(home[i + 1][0], home[i + 1][1]);
		}
		System.out.println(Math.min(home[0][0], Math.min(home[0][1], home[0][2])));
	}

}
