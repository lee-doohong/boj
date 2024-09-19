package boj1033;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	
	int N;
	int[] nArr;
	
	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	Stack<Integer[]> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	void solution() throws IOException {
		 N = Integer.parseInt(br.readLine());
		 nArr = new int[N];
		 
		 Arrays.fill(nArr, 0);
		 
		 for (int i = 0; i < N - 1 ; i++) {
			 stack.push(Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new));
			 //첫번째 것은 그냥 해주고 그다음 부터는 둘다 0 0 이면 안해준다
		 }
		 
		 Integer[] firstQ = stack.pop();
		 nArr()
	}
	
	int[] smallSol(int n, int m, int p, int q) {
		if (n == 0) n = 1;
		if (m == 0) m = 1;
		
		int tmpLCM = LCM(n, m);
		return new int[]{(tmpLCM / n) * p, (tmpLCM / m) * q};
	}
	
	int[] trim(int n, int m) {
		return new int[] {n / GCD(n, m), m / GCD(n, m)};
	}
	
	int GCD(int n, int m) {
		int a = n > m ? n : m;
		int b = n < m ? n : m;
		
		int r = a % b;
		
		if (r == 0) return b;
		else return GCD(a / b, r);
	}
	
	int LCM(int n, int m) {
		return (n * m) / GCD(n, m);
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
