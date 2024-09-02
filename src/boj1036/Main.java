package boj1036;

import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Main {

	int N, K;

	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	final BigInteger TS = new BigInteger("36");
	
	List<thirtySix> listTS = new ArrayList<>();

	BigInteger finalResult = new BigInteger("0");
	
	
	//A는 36진수 기준 10
	//Z는 36진수 기준 35
	class thirtySix implements Comparable<thirtySix>{
		int indexN;
		BigInteger toZ = new BigInteger("0");
		
		thirtySix(int indexN) {
			this.indexN = indexN;
		}

		thirtySix changeToZ(BigInteger input) {
			this.toZ = input;
			return this;
		}
		
		public String toString() {
			return String.format("index : %d, toZ : %s", indexN, toZ.toString());
		}

		@Override
		public int compareTo(thirtySix o) {
			// TODO Auto-generated method stub
			return o.toZ.compareTo(this.toZ);//큰수를 작은수랑 비교하면 1을 리턴한다.//즉 toZ 기준으로 내림차순 배열
		}
	}
	
	void solution() throws IOException {
		//일단 리스트 초기화
		for (int i = 0; i < 36; i++) {//0부터 35까지 객체 저장
			listTS.add(new thirtySix(i));
		}
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			thirtysixToTen(br.readLine().toCharArray());
		}
		
		Collections.sort(listTS);
		
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < K; i++) {
			finalResult = finalResult.add(listTS.get(i).toZ);
		}
		
		pl("listTs : " + listTS);
		
		tenToThirtySix(finalResult);
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
			
			BigInteger toPutListTS = new BigInteger("35").subtract(tmp);
			for (int j = i; j < input.length - 1; j++) {
				toPutListTS = toPutListTS.multiply(TS);
			}
			
//			pl(String.format("tmp : %d, toPutListTS : %s", tmp, toPutListTS.toString()));
			
			//각 숫자가 Z가 되면 얼마나 커지는지를 listTS에 저장
			listTS.set(Integer.parseInt(tmp.toString()), 
					listTS.get(Integer.parseInt(tmp.toString())).
					changeToZ(listTS.get(Integer.parseInt(tmp.toString())).toZ.add(toPutListTS)));
			
			BigInteger tmp2 = returnN.multiply(TS).add(tmp);
			returnN = tmp2;
		}

//		pl(String.format("[thirysixToTen] return : %s", returnN.toString()));

		finalResult = finalResult.add(returnN);
		
		return returnN;
	}
	
	void tenToThirtySix(BigInteger input) {//걍 출력해주는것
		StringBuilder sb = new StringBuilder();
		if (input.compareTo(new BigInteger("0")) == 0) {
			sb.append(0);
		}
		
		while(input.compareTo(new BigInteger("0")) != 0) {
			int tmpN = Integer.parseInt(input.remainder(TS).toString());
//			pl(String.format("[tenToThirtySix] tmpN : %d", tmpN));
			if (tmpN < 10) {
				sb.append((char)(tmpN + '0'));
			} else {
				sb.append((char)(tmpN + '7'));
			}
			input = input.divide(TS);
		}
//		pl(sb.toString());
		pl(new StringBuffer(sb.toString()).reverse().toString());
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
