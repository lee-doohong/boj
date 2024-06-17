	package boj1937;
	
	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.OutputStreamWriter;
	import java.util.*;
	
	
	//4
	//14 9 12 10
	//1 11 5 4
	//7 15 2 13
	//6 3 16 8
	//ans : 4
	
	public class Main {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		final int[][] EWSN = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		
		int N;
		int[][] dp, map;
		boolean[][] visited;
		int result = Integer.MIN_VALUE;
		
		void solution() throws IOException {
			N = Integer.parseInt(br.readLine());
			dp = new int[N][N];
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			
			dpSet();
			
			//한번 먹고 지나간 곳은 체크할 필요가 없는게 어짜피 현재 값보다 작은 곳은 못간다.
			//-> 그건 또 아닌게 다른곳에서 먹으면서 넘어올 가능성이 있음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] == false) {
						result = Math.max(result, dfs(i, j));
					}
				}
			}
			
			pl(result);
		}
		
		void dpSet() { 
			for (int i = 0; i < dp.length; i++) {
				Arrays.fill(dp[i], 1);
			}
		}
		
		int dfs(int i, int j) {
//			pl(String.format("[dfs] i : %d, j : %d", i, j));
//			deepPl(dp);
			if (visited[i][j]) return dp[i][j];//이미 방문 했던 경우에는 해당 dp값 return;
			visited[i][j] = true;
			
			int tmpN = -1;
			for (int[] arrTmp : EWSN) {
				int tmpI = i + arrTmp[0];
				int tmpJ = j + arrTmp[1];
				if (tmpI >= 0 && tmpI <= N - 1 && tmpJ >= 0 && tmpJ <= N - 1) {//일단 범위 안이라면
					if (map[tmpI][tmpJ] < map[i][j]) {//나보다 작은 곳이면 흡수 가능
						 tmpN = Math.max(dfs(tmpI, tmpJ), tmpN);
					}
				}
			}
			if (tmpN != -1) {
//				pl(String.format("tmpN : %d", tmpN));
				dp[i][j] += tmpN;
			}
			return dp[i][j];
		}
		
//		void dfs (int i, int j) {
//	//		pl(String.format("[dfs]i : %d, j : %d", i , j));
//			visited[i][j] = true;
//			for (int[] arrTmp : EWSN) {
//				//나보다 작은 값이면 모두 더해준다?
//				
//				int tmpI = i + arrTmp[0];
//				int tmpJ = j + arrTmp[1];
//				if (tmpI >= 0 && tmpI <= N - 1 && tmpJ >= 0 && tmpJ <= N - 1 //범위 안이라면
//						&& map[i][j] < map[tmpI][tmpJ] // 새로 갈 곳이 더 크다면
//						&& dp[tmpI][tmpJ] < dp[i][j] + 1
//						) { //
//	//				pl(String.format("[for]i : %d, j : %d, tmpI : %d, tmpJ : %d", i , j, tmpI, tmpJ));
//					dp[tmpI][tmpJ] = dp[i][j] + 1;
//	//				deepPl(dp);
//					result = Math.max(dp[tmpI][tmpJ], result);
//					dfs(tmpI, tmpJ);
//				}
//			}
//		}
	
		public static void main(String[] agrs) throws IOException {
			new Main().solution();
		}
		
		void pl(Object o) {
			System.out.println(o);
		}
		
		void deepPl(int[][] arr) {
			for (int i = 0; i < arr.length; i++) {
				pl(Arrays.toString(arr[i]));
			}
		}
	}