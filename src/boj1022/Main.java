package boj1022;

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
		
		for (int i : buildings) {
			maxBuildings = Math.max(maxBuildings, findBuildings(i));
		}
		
		pl(maxBuildings);
	}
	
	int findBuildings(int index) {
		double maxIncline;
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
