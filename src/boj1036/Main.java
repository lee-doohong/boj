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
		
		tenToThirtySix(thirtysixToTen("HELLO".toCharArray()));
	}
	
	BigInteger thirtysixToTen(char[] input) {
		BigInteger returnN = new BigInteger("0");
		BigInteger tmp = null;
		
		for (int i = 0; i < input.length; i++) {
			if (input[i] < ':') {
				tmp = new BigInteger(String.valueOf(input[i]));
			} else {
				tmp = new BigInteger(String.valueOf(input[i] - '7'));
			}
			
			pl(String.format("[thirysixToTen] returnN : %s", returnN.toString()));
			
			BigInteger tmp2 = returnN.multiply(TS).add(tmp);
			returnN = tmp2;
		}

		pl(String.format("[thirysixToTen] return : %s", returnN.toString()));
		return returnN;
	}
	
	void tenToThirtySix(BigInteger input) {//걍 출력해주는것
		StringBuilder sb = new StringBuilder();
		
		while(input.compareTo(new BigInteger("0")) != 0) {
			int tmpN = Integer.parseInt(input.remainder(TS).toString());
			pl(String.format("[tenToThirtySix] tmpN : %d", tmpN));
			if (tmpN < 10) {
				sb.append((char)(tmpN + '0'));
			} else {
				sb.append((char)(tmpN + '7'));
			}
			input = input.divide(TS);
		}
		pl(sb.toString());
		pl(new StringBuffer(sb.toString()).reverse().toString());
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
