package boj1036;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {

	int N;

	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	final BigInteger TS = new BigInteger("36");

	//A는 36진수 기준 10
	//Z는 36진수 기준 35
	
	void solution() throws IOException {
//		N = Integer.parseInt(br.readLine());
		
		pl(('A' - '7'));
		pl(('Z' - '7'));
		
		pl(thirtysixToTen("HELLO".toCharArray()));
	}
	
	BigInteger thirtysixToTen(char[] input) {
		BigInteger tmp = null;
		
		for (int i = 0; i < input.length; i++) {
			if (input[i] < ':') {
				tmp = new BigInteger(String.valueOf(input[i]));
			} else {
				tmp = new BigInteger(String.valueOf(input[i - '7']));
			}
			
			for (int j = 0; j < i; j++) {
				tmp = new BigInteger(tmp.multiply(TS).toString());
			}
		}

		return tmp;
	}
	
	void thirtysixToTen(BigInteger input) {//걍 출력해주는것
		char[] result = new char[50];
		StringBuilder sb = new StringBuilder();
		
		
		int index = 0;
		while(input.compareTo(new BigInteger("0")) != 0) {
			int tmpN = Integer.parseInt(input.remainder(TS).toString());
			if (tmpN < 10) {
				sb.append((char)(tmpN + '0'));
			} else {
				sb.append((char)(tmpN + '7'));
			}
			input = input.divide(TS);
		}
		pl(new StringBuffer(sb.toString()).reverse().toString());
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
