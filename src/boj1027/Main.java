package boj1027;

import java.io.*;
import java.util.*;

public class Main {

	int N;
	int[] buildings;
	int maxBuildings; 

	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	void solution() throws IOException {
		N = Integer.parseInt(br.readLine());
		buildings = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		maxBuildings = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			maxBuildings = Math.max(maxBuildings, findBuildings(i));
		}
		
		pl(maxBuildings);
	}
	
	int findBuildings(int index) {
		double maxInclineLeft = (double)Integer.MIN_VALUE;
		double maxInclineRight = (double)Integer.MIN_VALUE;
		int count = 0;
		
		for (int i = index - 1; i >= 0; i--) {
			double incline = findIncline(index, i);
//			pl(String.format("index : %d, i : %d, incline : %f, maxInclineLeft : %f", index, i, incline, maxInclineLeft));
			if (incline > maxInclineLeft) {
				count++;
//				pl("count :" + count);
				maxInclineLeft = incline;
			}
		}
		
		for (int i = index + 1; i < N; i++) {
			double incline = findIncline(index, i);
//			pl(String.format("index : %d, i : %d, incline : %f, maxInclineRight : %f", index, i, incline, maxInclineRight));
			if (incline > maxInclineRight) {
				count++;
//				pl("count :" + count);
				maxInclineRight = incline;
			}
		}
		
//		pl(String.format("index : %d, count : %d", index, count));
		
		return count;
	}
	
	double findIncline(int index, int i) {
		return (double)(buildings[i] - buildings[index]) / (double)Math.abs(i - index);
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
