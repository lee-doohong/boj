package boj16638;

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

	   //첫번째 dfs에 실어보낼 것
	   int[] firstSelect = new int[N];
	   
	   for (int i = 0; i < N; i++) {
		   if (arr[i].equals("*")) {
			   firstSelect[i] = 2;
		   }
	   }
	   
	   dfs(1, firstSelect);
	   
	   pl(finalResult);	   
//	   pl(Arrays.toString(arr));
   }
   
   public void calculation(int[] select) {
//	   pl(String.format("[calculation] select : %s", Arrays.toString(select)));
	   String[] tmpArr = arr.clone();
	   
	   for (int i = 1; i < N; i += 2) {//1번 우선순위 모두 처리
		   if (select[i] == 1) {
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   
	   for (int i = 1; i < N; i += 2) {
		   if (select[i] == 2) {//연산자를 찾았다는 의미
			   //나 바로 앞에 값 찾기
			   //나 바로 뒤에 값 찾기
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   
	   for (int i = 1; i < N; i += 2) {
		   if (select[i] != 1 && select[i] != 2) {//괄호 또는 *가 아닌 연산
			   //나 바로 앞에 값 찾기
			   //나 바로 뒤에 값 찾기
			   tmpArr[i] = cal(fn(tmpArr, i, false), fn(tmpArr, i, true), arr[i]);
		   }
	   }
	   //모든 연산 종료
//	   pl(String.format("[모든연산종료후] tmpArr : %s", Arrays.toString(tmpArr)));
	   for (int i = 0; i < N; i ++) {
		   if (tmpArr[i] != null) { 					   
			   finalResult = Math.max(Integer.parseInt(tmpArr[i]), finalResult);
		   }
	   }
   }
   
   public String fn(String[] tmpArr, int i, boolean flag) {
//	   pl(String.format("[fn] tmpArr: %s, i: %d, flag : %b", Arrays.toString(tmpArr), i, flag));
	   if (!flag) {
		   for (int j = i - 1; j >= 0; j--) {
			   if (tmpArr[j] != null 
					   && !tmpArr[j].equals("+")
					   && !tmpArr[j].equals("-") 
					   && !tmpArr[j].contentEquals("*")) {
				   String tmpResult = tmpArr[j]; 
				   tmpArr[j] = null;
				   return tmpResult;
			   }
		   }
	   } else {
		   for (int j = i + 1; j >= 0; j++) {
			   if (tmpArr[j] != null 
					   && !tmpArr[j].equals("+")
					   && !tmpArr[j].equals("-") 
					   && !tmpArr[j].contentEquals("*")) {
				   String tmpResult = tmpArr[j]; 
				   tmpArr[j] = null;
				   return tmpResult;
			   }
		   }
	   }
	   return null;
   }
   
   public void dfs(int index, int[] select) {
	   if (index >= N) {//이미 범위 밖
		   calculation(select);//연산으로 보내야 함
		   return;
	   }

	   //배열로 하자..
	   int[] newSelect = select.clone();
	   
	   //괄호를 친다
	   select[index] = 1;
	   dfs(index + 4, select);
	   
	   //괄호를 치지 않는다
	   dfs(index + 2, newSelect);
   }
   
   public String cal(String a, String b, String func) {
//	   pl(String.format("[cal] a: %s, b: %s, func: %s", a, b, func));
	   int n1 = ip(a);
	   int n2 = ip(b);
	   switch(func) {
		   case "+" :
			   return String.valueOf(n1 + n2);
		   case "-" :
			   return String.valueOf(n1 - n2);
		   case "*" :
			   return String.valueOf(n1 * n2);
		   default :
			   return "0";
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
 