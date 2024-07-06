package boj16637;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

   int N;
   int finalResult;
   String[] arr;
   
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

   public void solution() throws IOException {
	   N = Integer.parseInt(br.readLine());
	   arr = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
	   
	   finalResult = Integer.MIN_VALUE;
	   
	   dfs(0, Integer.parseInt(arr[0]));
	   
	   pl(finalResult);
   }
   
   
   
   public void dfs(int index, int receivedValue) {//최초에는 index 1과 넘김값 0을 받는다.
	   int result;
	   
	   //종료 조건 설정
	   if (index + 3 >= N) {
		   if (index + 2 <= N) {
			   result = cal(receivedValue, ip(arr[index + 2]), arr[index + 1]);
			   finalResult = Integer.max(result, finalResult);
		   } else {
			   finalResult = Integer.max(receivedValue, finalResult);
		   }
		   return;
	   }
	   
	   //index + 2 와 index + 4가 index + 3의 연산을 하는 경우
	   dfs(index + 4, cal(receivedValue, 
			   cal(ip(arr[index + 2]), ip(arr[index + 4]), arr[index + 3])
			   , arr[index + 1] 
			   ));
	   
	   dfs(index + 2, cal(receivedValue, ip(arr[index + 2]), arr[index + 1]));
   }
   
   public int cal(int a, int b, String func) {
	   switch(func) {
		   case "+" :
			   return a + b;
		   case "-" :
			   return a - b;
		   case "*" :
			   return a * b;
		   default :
			   return 0;
	   }
   }
   
   public static int ip(String s) {
	   return Integer.parseInt(s);
   }
   
   public static void main(String[] args) throws IOException {
      new Main().solution();
   }
   
   public void pl(Object o) {
      System.out.println(o);
   }
   
   public void deepPl(int[][] l) {
      for(int[] lTmp : l) {
         pl(Arrays.toString(lTmp));
      };
   }
}
 
