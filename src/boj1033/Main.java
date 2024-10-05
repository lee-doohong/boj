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
	boolean[] visited;
	
	final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	Stack<Long[]> stack = new Stack<>();
	
	public static void main(String[] args) throws IOException {
		new Main().solution();
	}
	
	void solution() throws IOException {
		 N = Integer.parseInt(br.readLine());
		 
		 if (N == 1) {
			 pl(1);
			 return;
		 }
		 
		 adj = new ArrayList[N];
		 for (int i = 0; i < N; i++) {
			 adj[i] = new ArrayList();
			 adj[i].add(i);
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
		 
//		 pl(String.format("firstQ : %s", Arrays.toString(firstQ)));
		 nArr[firstQ[0].intValue()] = (long)firstQ[2];
		 nArr[firstQ[1].intValue()] = (long)firstQ[3];
		 
		 adj[firstQ[0].intValue()].add(firstQ[1].intValue());
		 adj[firstQ[1].intValue()].add(firstQ[0].intValue());

//		 plAdj();
//		 plNArr();
//		 
		 while(!stack.isEmpty()) {//그냥 stack에 넣었을 뿐 특별한 의미는 없다 지금 상황에서는
			 Long[] tmpQ = stack.pop();
			 //대상 index 수 두개를 i와 j로 지정해준디 ㅏ.
			 long i = nArr[tmpQ[0].intValue()];
			 long j = nArr[tmpQ[1].intValue()];
			 int indexI = tmpQ[0].intValue();
			 int indexJ = tmpQ[1].intValue();
			 
//			 이미 해당 비율인 경우에는 그냥 넘어간다 특별한 조치 없이 continue 돌린다;
			 if (i != 0L && j != 0L) {//i도 0이 아니고 j도 0이 아닌경우..
				 long[] trimmedIJ = trim(i, j);
				 if (i == tmpQ[2] && j == tmpQ[3]) {
					 continue;
				 }
			 }
			 
			 smallSol(i, j, tmpQ[2].intValue(), tmpQ[3].intValue(), indexI, indexJ);
//			 plAdj();
//			 plNArr();
		 }
//		 pl(Arrays.toString(nArrs));
//		 output		 
		 StringBuilder sb = new StringBuilder();
		 
		 long GCDtmp = nArr[0];
		 for (int i = 1; i < N; i++) {
			 GCDtmp = GCD(GCDtmp, nArr[i]);
		 }
		 
		 for (int i = 0; i < N; i++) {
			 nArr[i] /= GCDtmp;
		 }
		 
		 for (long n : nArr) {
			 sb.append(n);
			 sb.append(" ");
		 }
		 
		 pl(sb.toString().trim());
	}
	
	void smallSol(long n, long m, int p, int q, int indexN, int indexM) {
//		pl(String.format("[smallSol] n : %d, m : %d, p : %d, q : %d , indexN : %d, indexM : %d", n, m, p, q, indexN, indexM));
		if (n == 0L) n = 1L;
		if (m == 0L) m = 1L;
		
		//두 숫자의 최소공배수를 먼저 구해준다.
		long tmpLCM = LCM(n, m);
		
		//n에 딸려있는 식구들에 대해서 곱을 해준다.
		long tmp1 = (tmpLCM / n) * p;
		visited = new boolean[N];
		dfs(indexN, tmp1);
//		for (int i : adj[indexN]) {
//			//곱해야 하는 수는 무엇인가
//			dfs(i, tmp1);
//			
//			if (nArr[i] == 0L) nArr[i] = 1L;
//			nArr[i] *= tmp1;
//		}
		//m에 딸려있는 식구들에 대해서 곱을 해준다.
		long tmp2 = (tmpLCM / m) * q;
		dfs(indexM, tmp2);
//		for (int j : adj[indexM]) {
//			if (nArr[j] == 0L) nArr[j] = 1L;
//			nArr[j] *= tmp2;
//		}
		
		adj[indexN].add(indexM);
		adj[indexM].add(indexN);
		//return new long[]{tmpLCM * p, tmpLCM * q};
	}
	
	void dfs(int index, long tmpN) {
		if (visited[index]) return;
		
		visited[index] = true;
		if (nArr[index] == 0L) nArr[index] = 1L;
		nArr[index] *= tmpN;
		for (int i : adj[index]) {
			dfs(i, tmpN);
		}
	}
	
	void plNArr() {
		pl("plNArr");
		pl(Arrays.toString(nArr));
	}
	
	void plAdj() {
		pl("plAdj");
		for (List l : adj) {
			System.out.print(l);
		}
		pl("");
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
	
	long LCM(long n, long m) {
		return (n * m) / GCD(n, m);
	}
	
	void pl(Object o) {
		System.out.println(o);
	}
}
