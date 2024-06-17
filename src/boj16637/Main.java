package boj16637;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

   int N;
   int sum;
   String[] arr;
   
   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

   public void solution() throws IOException {
	   pl("solution start");
	   
	   N = Integer.parseInt(br.readLine());
	   arr = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
	   
	   dfs(1, Integer.parseInt(arr[0]));
	   
	   
   }
   
   
   
   public void dfs(int index, int receivedValue) {//최초에는 index 1과 넘김값 0을 받는다.
	   //여기서 넘김 수는 
	   //종료 조건 생성해 줘야함
	   
	   ////괄호를 치는 경우
	   //괄호안의 값 우선 계산
	   int throwResult = cal(Integer.parseInt(arr[index - 1]), 
		   		Integer.parseInt(arr[index + 1]), 
		   		arr[index]);
	   
	   //넘김값과 괄호안 값을 이전연산 해줌
	   throwResult = cal(receivedValue, throwResult, arr[index - 2]);  

	   dfs(index + 4, throwResult);
	   
	   ////괄호를 치지 않는 경우
	   throwResult = cal(receivedValue, Integer.parseInt(arr[index - 1]), arr[index - 2]); 
	   
	   dfs(index + 2, throwResult);
	   //종료에  대해서 고민해봐야됨.
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
 
