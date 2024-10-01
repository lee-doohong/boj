package boj1033;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	//깊이 우선 탐색?
	//무엇에 무엇이 연결되어 있는지 정리해주자
	//
	int N;
	long[] nArr;
	List<Integer>[] adj;//어떤 것이 어디에 인접해 있는지 찾아주는것//처리해주고 난 다음에만 넣어준다.
	
	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	Stack<Long[]> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	void solution() throws IOException {
		 N = Integer.parseInt(br.readLine());
		 
		 adj = new ArrayList[N];
		 for (int i = 0; i < N; i++) {
			 adj[i] = new ArrayList();
		 }
		 
		 nArr = new long[N];
		 
		 for (int i = 0; i < N - 1 ; i++) {
			 //Input을 받아서 비율 부분은 최소 비율(?)로 바꿔준다.
			 Long[] tmp = Arrays.stream(br.readLine().split(" ")).map(Long::parseLong).toArray(Long[]::new);
			 long[] tmp2 = trim((long)tmp[2], (long)tmp[3]);
			 tmp[2] = tmp2[0];  tmp[3] = tmp2[1];
			 stack.push(tmp);
			 //첫번째 것은 그냥 해주고 그다음 부터는 둘다 0 0 이면 안해준다
		 }
		 
		 Long[] firstQ = stack.pop();//첫번째 것은 바꿔주고 시작한다.
		 nArr[firstQ[0].intValue()] = (long)firstQ[2];
		 nArr[firstQ[1].intValue()] = (long)firstQ[3];
		 
		 while(!stack.isEmpty()) {
			 Long[] tmpQ = stack.pop();
			 //대상 index 수 두개를 i와 j로 지정해준디 ㅏ.
			 long i = nArr[tmpQ[0].intValue()];
			 long j = nArr[tmpQ[1].intValue()];
			 int indexI = tmpQ[0].intValue();
			 int indexJ = tmpQ[1].intValue();
			 
//			 이미 해당 비율인 경우도 특별한 조치 없이 continue 돌린다;
			 if (i != 0L && j != 0L) {//i도 0이 아니고 j도 0이 아닌경우..
				 long[] trimmedIJ = trim(i, j);
				 if (i == tmpQ[2] && j == tmpQ[3]) {
					 continue;
				 }
			 }
			 
			 //이제 하나는 0이고 하나는 0이 아니다
			 boolean std = (i == 0 ? true : false);
			 //std 첫번째 수가 0인 경우는 true, 두번째 수가 0인 경우는 false
			 
			 if(std) {
				 //
				 for (int k = 0; k < N; k++) {
					 nArr[k] *= tmpQ[3];
				 }

				 nArr[indexI] = j * tmpQ[2];
			 } else {
				 for (int k = 0; k < N; k++) {
					 nArr[k] *= tmpQ[2];
				 }
				 
				 nArr[indexJ] = i * tmpQ[3];
			 }
			 
		 }
//		 pl(Arrays.toString(nArrs));
//		 output		 
		 StringBuilder sb = new StringBuilder();
		 
		 for (long n : nArr) {
			 sb.append(n);
			 sb.append(" ");
		 }
		 
		 pl(sb.toString().trim());
	}
	
	int[] smallSol(int n, int m, int p, int q) {
		if (n == 0) n = 1;
		if (m == 0) m = 1;
		
		int tmpLCM = LCM(n, m);
		return new int[]{tmpLCM * p, tmpLCM * q};
	}
	
	long[] trim(long n, long m) {
//		pl(String.format("[trim] GCD(%d, %d) = %d", n, m, GCD(n, m)));
//		pl(String.format("[trim]return : %s", Arrays.toString(new Integer[] {n / GCD(n, m), m / GCD(n, m)})));
		return new long[] {n / GCD(n, m), m / GCD(n, m)};
	}
	
	long GCD(long n, long m) {
//		pl(String.format("[GCD] n = %d, m = %d", n, m));
		long a = n > m ? n : m;
		long b = n < m ? n : m;
		
		long r = a % b;
		
		if (r == 0) return b;
		else return GCD(b, r);
	}
	
	int LCM(int n, int m) {
		return (n * m) / GCD(n, m);
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
