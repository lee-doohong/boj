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
		
		pl("hello world");
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());
		
	}
	
//	이 그림판 자체가 여러겹의 테두리로 겹겹이 되어 있는 구조 이다.
//	모든 결과 값은 별개의 테두리로 구성되어 있을 수도 있다.
//	그러므로 각 값들은 별개로 구해줘야 한다.
//	그럼 각 값을 구하는 산식을 구한다음 r1, c1 -> r2, c2로 값을 차례로 조회 하는 식으로 구해야 한다.
//	일단 특정 좌표를 주면 해당 좌표에 해당 하는 값을 뱉는 함수를 구하는 것이 먼저임.
//	1. 두 값중 절대 값이 큰 값이 몇번째 테두리 인지 알려주는 것이다.
//	2. 테두리를 기준으로 이게 어디에 있는 값인지 찾아내야 한다.
//	-1-1 -10  -11
//	 0-1          0 1
//	  1,-1 1,0   1,1
//	A구역 x가 y보다 크다. 같은 경우에는 둘다 양수이다.
//
//	B구역 x가 y보다 작사. 같은 경우에는 둘다 음수이다.
//	1 -> 9 -> 25
//	- 기준 점은 (2n + 1)^2
//	- x >= y 경우 /// 절대값이 큰수가 
//	- x < y 경우 



	
//	int findNumber(int x, int y) { 
//		
//		
//		
//	}
	
	public static void main(String[] args) throws IOException{ 
		new Main().solution();
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
