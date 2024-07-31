package boj1022;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	int r1, c1, r2, c2;

	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	void solution() throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
		
		
	}
	
	//x와 y를 받아서 그중에 큰 절대값 수 n을 찾는다.
	//해당 값에서 기준 수 N은 = (1+2n)의 제곱 수
	//여기서 x가 y보다 큰 경우와 y가 x보다 큰경우를 나눠서 계산할 수가 있는데....
	//
	
	int findNumber(int x, int y) { 
		
		
		
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}

}
