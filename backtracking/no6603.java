package backtracking;

import java.io.IOException;
import java.util.Scanner;

public class no6603 {
	static int S[];
	static int K;
	public static void dfs(String result, int index, int cnt) {
		if(cnt == 6) {
			System.out.println(result);
			return;
		}
		for(int i = index ; i < K ;i++) {
			dfs(result+S[i]+" ", i+1, cnt+1);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		while (true) {
			K = scan.nextInt();
			if (K == 0)
				break;
			S = new int[K];
			for (int i = 0; i < K; i++) {
				S[i] = scan.nextInt();
			}
			dfs("", 0,0);
			System.out.println();
		}
	}

}
