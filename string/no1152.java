package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class no1152 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String word = in.readLine();
		int count = 0;
		boolean start = false;
		boolean end = false;
		for(int i = 0 ; i < word.length(); i++) {
			if(word.charAt(i)!=' ')
				start = true;
			if(start == true && (word.charAt(i) == ' '|| i+1==word.length())) {
				
				count++;
			}
			
		}
		
		
		System.out.println(count);
	}

}
